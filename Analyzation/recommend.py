from pymongo import MongoClient
import pandas as pd
import pymysql
import random
from konlpy.tag import Kkma
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel

#코사인 유사도 구하여 디비에 넣는 함수
def makeSim(bigdata):
    data = []
    cnt = 0;
    #디비에 있는 맛집데이터를 5만개 가져옴
    for i in bigdata.find({"id" : {"$lt" : 50001}}):
        if(cnt==50001):
            break;
        data.append(i)
        # print(i)
        cnt += 1

    stores = []  # 음식점
    reviews = []  # 리뷰
    menus = []  # 메뉴

    review_col = (
        "id",  # 리뷰 고유번호
        "store",  # 음식점 고유번호
        "user",  # 유저 고유번호
        "score",  # 평점
        "content",  # 리뷰 내용
        "reg_time",  # 리뷰 등록 시간
    )

    store_col = (
        "id",  # 음식점 고유번호
        "store_name",  # 음식점 이름
        "branch",  # 음식점 지점 여부
        "area",  # 음식점 위치
        "tel",  # 음식점 번호
        "address",  # 음식점 주소
        "latitude",  # 음식점 위도
        "longitude",  # 음식점 경도
        "review_cnt",  # 리뷰 개수
        "category",  # 음식점 카테고리
    )

    menu_col = (
        "id",  # 메뉴 리스트
        "menu_name",  # 메뉴 이름
        "price",  # 메뉴 가격
    )

    # 맛집데이터에서 store, review 데이터를 뽑아 dataframe을 만듬
    for d in data:

        categories = [c["category"] for c in d["category_list"]]
        stores.append(
            [
                d["id"],
                d["name"],
                d["branch"],
                d["area"],
                d["tel"],
                d["address"],
                d["latitude"],
                d["longitude"],
                d["review_cnt"],
                "|".join(categories),
            ]
        )

        for review in d["review_list"]:
            r = review["review_info"]
            u = review["writer_info"]

            reviews.append(
                [r["id"], d["id"], u["id"], r["score"], r["content"], r["reg_time"]]
            )
        for menu in d["menu_list"]:
            men = menu["menu"]
            price = menu["price"]
            menus.append([d["id"], men, price])

    store_frame = pd.DataFrame(data=stores, columns=store_col)
    review_frame = pd.DataFrame(data=reviews, columns=review_col)
    menu_frame = pd.DataFrame(data=menus, columns=menu_col)

    result = []
    # data = dataframes["reviews"]
    data = pd.merge(
        store_frame, review_frame, left_on="id", right_on="store", how= "left"
    )

    sum = pd.merge(
        data, menu_frame, left_on="id_x", right_on="id", how= "left"
    )

    print(sum)

    s = ""
    pre = 1
    cnt = 1;
    # print(data)
    hole = []
    for i, row in sum.iterrows():
        store = row['id_x']
        content = row['content']
        category = row['category']
        menu = row['menu_name']

        #store id가 바꼇을경우 이전 store 정보를 기록하고 현재 store 기록을 시작
        if(pre != store):
            # if(str != '' or str != ' ') :
            review = s.replace('\n', ' ')
            review += ' '
            review += str(pre + 100)
            review += ' '
            result.append([pre, review])
            s = ''
            #nan이 아니면 값을 붙여준다
            if (type(content) != float):
                s += ' '
                s += content

            if (type(category) != float):
                s += ' '
                s += category

            if (type(menu) != float):
                s += ' '
                s += menu

            pre = store
            cnt += 1

        #전 store와 id 와 같은경우
        else:
            if (type(content) != float):
                s += ' '
                s += content

            if (type(category) != float):
                s += ' '
                s += category

            if (type(menu) != float):
                s += ' '
                s += menu

    #마지막 행 넣기
    review = s.replace('\n', ' ')
    review += ' '
    review += str(pre + 100)
    review += ' '
    result.append([pre, review])
    #결과로 dataframe 만듬
    result_columns = (
        "id",
        "content",
    )
    result_frame = pd.DataFrame(data=result, columns=result_columns)
    print(result_frame)
    print("코엔엘시작")

    tokens = []
    kkma = Kkma()
    index = 0
    ##koNLPY로 단어 분해
    for i, row in result_frame.iterrows():
        content = row['content']
        store = row['id']

        # print(index)
        # if(index == 50000):
        #     break;
        #
        # index += 1
        # print(content)
        try:
            tmp = kkma.nouns(content)
        except Exception as e:
            # index -= 1
            # print(e)
            continue;
        for token in tmp:
            s += ' ' + token
            # print(token)
        # print(store, s)
        tokens.append([store, s])
        s = ''
    #분해된 결과 dataframe으로 만듬
    token_frame = pd.DataFrame(data=tokens, columns=result_columns)
    # print(token_frame)
    #tfidf만듬
    tfidf = TfidfVectorizer()
    tfidf_matrix = tfidf.fit_transform(token_frame.content)
    #코사인유사도 행렬 생성
    cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix, dense_output=True)
    print("코엔엘끝")
    my_client = MongoClient("")
    mydb = my_client['']
    mycol = mydb['']

    #생성된 행렬 index 붙여서 json형태로 db에 넣음
    index = 0
    for i in cosine_sim:
        # print(index, i)
        list = []
        list.extend(i)
        # print(index, list)
        query = {'index': str(index), 'content': list}
        mycol.insert_one(query)
        index += 1

#콜드스타트로 쓸 랭킹 구하여 디비에 넣는 함수
def ColdStart(bigdata, rank):
    data = []
    cnt = 0;
    #디비에 있는 맛집데이터를 5만개 가져옴
    for i in bigdata.find():
        if(cnt==50001):
            break;
        data.append(i)
        # print(i)
        cnt += 1

    stores = []  # 음식점
    reviews = []  # 리뷰

    review_col = (
        "id",  # 리뷰 고유번호
        "store",  # 음식점 고유번호
        "user",  # 유저 고유번호
        "score",  # 평점
        "content",  # 리뷰 내용
        "reg_time",  # 리뷰 등록 시간
    )

    store_col = (
        "id",  # 음식점 고유번호
        "store_name",  # 음식점 이름
        "branch",  # 음식점 지점 여부
        "area",  # 음식점 위치
        "tel",  # 음식점 번호
        "address",  # 음식점 주소
        "latitude",  # 음식점 위도
        "longitude",  # 음식점 경도
        "review_cnt",  # 리뷰 개수
        "category",  # 음식점 카테고리
    )

    #맛집데이터에서 store, review 데이터를 뽑아 dataframe을 만듬
    for d in data:

        categories = [c["category"] for c in d["category_list"]]
        stores.append(
            [
                d["id"],
                d["name"],
                d["branch"],
                d["area"],
                d["tel"],
                d["address"],
                d["latitude"],
                d["longitude"],
                d["review_cnt"],
                "|".join(categories),
            ]
        )

        for review in d["review_list"]:
            r = review["review_info"]
            u = review["writer_info"]

            reviews.append(
                [r["id"], d["id"], u["id"], r["score"], r["content"], r["reg_time"]]
            )


    store_frame = pd.DataFrame(data=stores, columns=store_col)
    review_frame = pd.DataFrame(data=reviews, columns=review_col)
    # print(store_frame)
    # print(review_frame)
    # 두 dataframe을 id로 합침
    stores_reviews = pd.merge(
        store_frame, review_frame, left_on="id", right_on="store"
    )
    #리뷰가 10개 이상인 가게
    min_reviews = 10
    stores_reviews = stores_reviews[(stores_reviews['review_cnt'] >= min_reviews)]
    # print(stores_reviews)
    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()
    # print(scores)
    # score로 정렬
    scores_sort = scores.sort_values(by="score", ascending=False)
    # print(scores_sort)
    # rank를 적어서 디비에 넣음
    cnt = 1
    for i, row in scores_sort.iterrows():
        # print(int(row['id_x']))
        dic = bigdata.find_one({"id": int(row['id_x'])})
        dic['rank'] = cnt
        # print(dic)
        rank.insert_one(dic)
        cnt += 1

    # print(dic)

    return

#사용자 위시리스트에 있는 맛집을 통해 추천맛집을 리턴
def WishListRec(consine_sim, bigdata):
    #디비연결
    db = pymysql.connect(
        user='',
        passwd='',
        host='',
        db='',
        charset=''
    )

    #유저의 위시리스트에서 맛집id를 가져온다
    cursor = db.cursor()
    id = 3
    sql = f"SELECT spot_id FROM WishList WHERE type = '0' and user_id = {id}"
    cursor.execute(sql)
    result = cursor.fetchall()
    # print(result[0])
    # print(random.choice(result)[0])
    num = random.choice(result)[0]
    # print(num)
    #유사도 행렬에서 해당 아이디의 행을 가져옴 인덱스라서 -1해야됨
    li = consine_sim.find_one({"index": str(int(num) - 1)})
    # print(li)
    #유사도 부분을 인덱스를 붙여 리스트화하고 높은순으로 정렬
    tmp = list(enumerate(li['content']))
    tmp = sorted(tmp, key=lambda x: x[1], reverse=True)
    # print(tmp)
    arr = []
    #자신(유사도 1)을 제외하고 가장 높은 것 10개 뽑음
    for i in tmp[1:11]:
        # print(bigdata.find_one({"id": i[0] + 1}))
        arr.append(bigdata.find_one({"id": i[0] + 1}))

    # print(arr)
    return

#콜드스타트 얻기
def getColdStart(rank):
    data = []
    #디비에 있는 맛집데이터를 5만개 가져옴
    for i in rank.find():
        data.append(i)
    # print(data)
    return data
#store 정보얻기(1개)
def getStore(bigdata):
    print(bigdata.find_one({"id": 3}))
def main():

    my_client = MongoClient("")
    ssafy = my_client['']
    consine_sim = ssafy['']
    bigdata = ssafy['']
    rank = ssafy['']

    # ColdStart(bigdata, rank)
    # WishListRec(consine_sim, bigdata)
    makeSim(bigdata)
    # getColdStart(rank)
    # getStore(bigdata)
    return

if __name__ == "__main__":
    main()