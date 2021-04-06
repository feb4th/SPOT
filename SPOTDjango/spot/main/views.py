from django.http import HttpResponse
from pymongo import MongoClient
import os, json
import pymysql
import random
from rest_framework.decorators import api_view
from django.core.exceptions import ImproperlyConfigured

secret_file = os.path.abspath('secrets.json')

with open(secret_file) as f:
    secrets = json.loads(f.read())

def get_secret(setting, secrets=secrets):
    try:
        return secrets[setting]
    except KeyError:
        error_msg = "Set the {} environment variable".format(setting)
        raise ImproperlyConfigured(error_msg)

# MongoDB_NAME = get_secret('MongoDB_NAME')
# MongoDB_URL = get_secret('MongoDB_URL')
# MySQL_Name = get_secret('MySQL_Name')
# MySQL_USERNAME = get_secret('MySQL_USERNAME')
# MySQL_PASSWORD = get_secret('MySQL_PASSWORD')
# MySQL_URL = get_secret('MySQL_URL')


# Create your views here.
##콜드스타트 용 랭킹 100위
def rank(request):
    MongoDB_URL = get_secret('MongoDB_URL')
    MongoDB_NAME = get_secret('MongoDB_NAME')
    my_client = MongoClient(MongoDB_URL)
    ssafy = my_client[MongoDB_NAME]
    rank = ssafy['rank']
    data = []
    res = {}
    if request.method == 'GET':
        try:
            for i in rank.find():
                del i['_id']
                # print(i)
                data.append(i)

            res['message'] = 'success'
            res['contents'] = data
        except Exception as e:
            print(e)
            res['message'] = 'fail'
            return HttpResponse(json.dumps(res, ensure_ascii=False), status=500)

    return HttpResponse(json.dumps(res, ensure_ascii=False), status=200)

##가게정보
def store(request, id):
    MongoDB_URL = get_secret('MongoDB_URL')
    MongoDB_NAME = get_secret('MongoDB_NAME')
    my_client = MongoClient(MongoDB_URL )
    ssafy = my_client[MongoDB_NAME]
    bigdata = ssafy['bigdata']
    res = {}
    print(id)
    if request.method == 'GET':
        try:
            data = bigdata.find_one({"id": int(id)})
            # print(data)
            del data['_id']
            res['message'] = 'success'
            res['contents'] = data
        except Exception as e:
            res['message'] = 'fail'
            print(e)
            return HttpResponse(json.dumps(res, ensure_ascii=False), status=500)

    return HttpResponse(json.dumps(res, ensure_ascii=False), status=200)

##위시리스트의 store id로 회원에게 다른 store 추천
def recommendation(request, id):
    MongoDB_NAME = get_secret('MongoDB_NAME')
    MongoDB_URL = get_secret('MongoDB_URL')
    MySQL_Name = get_secret('MySQL_Name')
    MySQL_USERNAME = get_secret('MySQL_USERNAME')
    MySQL_PASSWORD = get_secret('MySQL_PASSWORD')
    MySQL_URL = get_secret('MySQL_URL')

    if request.method == 'GET':
        #디비연결
        #MySQL
        db = pymysql.connect(
            user=MySQL_USERNAME,
            passwd=MySQL_PASSWORD,
            host=MySQL_URL,
            db=MySQL_Name,
            charset='utf8'
        )

        #MongoDB
        my_client = MongoClient(MongoDB_URL)
        ssafy = my_client[MongoDB_NAME]
        consine_sim = ssafy['cosine_sim']
        bigdata = ssafy['bigdata']


        try:
            # 유저의 위시리스트에서 맛집id를 가져온다
            cursor = db.cursor()
            sql = f"SELECT spot_id FROM WishList WHERE type = '0' and user_id = {id}"
            cursor.execute(sql)
            result = cursor.fetchall()

           #위시리스트에서 랜덤으로 하나의 값을 가져와서 해당 store로 추천해줌 랜덤추천 아님
            num = random.choice(result)[0]
            # print(num)
            # 유사도 행렬에서 해당 아이디의 행을 가져옴 인덱스라서 -1해야됨
            li = consine_sim.find_one({"index": str(int(num) - 1)})
            # print(li)
            # 유사도 부분을 인덱스를 붙여 리스트화하고 높은순으로 정렬
            tmp = list(enumerate(li['content']))
            tmp = sorted(tmp, key=lambda x: x[1], reverse=True)
            # print(tmp)
            res = {}
            data = []
            # 자신(유사도 1)을 제외하고 가장 높은 것 10개 뽑음
            for i in tmp[1:11]:
                # print(bigdata.find_one({"id": i[0] + 1}))
                store = bigdata.find_one({"id": i[0] + 1})
                del store['_id']
                data.append(store)
            res['contents'] = data
            res['message'] = 'success'
        except Exception as e:
            res['message'] = 'fail'
            print(e)
            return HttpResponse(json.dumps(res, ensure_ascii=False), status=500)

    return HttpResponse(json.dumps(res, ensure_ascii=False), status=200)

@api_view(['POST'])
def reviewInsert(request):
    MongoDB_URL = get_secret('MongoDB_URL')
    MongoDB_NAME = get_secret('MongoDB_NAME')
    res = {}
    try:
        if request.method == 'POST':
            # MongoDB
            my_client = MongoClient(MongoDB_URL)
            ssafy = my_client[MongoDB_NAME]
            bigdata = ssafy['bigdata']

            def update_item_one(mongo, condition=None, update_value=None, db_name=None, collection_name=None):
                result = mongo[db_name][collection_name].update_one(filter=condition, update=update_value)
                return result

            review = json.loads(request.body, encoding='utf-8')
            id = review['id']
            del review['id']
            cursor = bigdata.find_one({'id': id})
            # print(cursor)
            review_list = cursor['review_list']
            # print(id)
            # print(review)
            review_list.append(review)
            # print(review_list)
            cnt = cursor["review_cnt"] + 1
            bigdata.update_one({"id": id}, {"$set": {"review_list": review_list, "review_cnt": cnt}})
            res['message'] = 'success'
    except Exception as e:
        res['message'] = 'fail'
        print(e)
        return HttpResponse(json.dumps(res, ensure_ascii=False), status=500)
    return HttpResponse(json.dumps(res, ensure_ascii=False), status=200)