from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from pymongo import MongoClient
import pandas as pd
import time

driver = webdriver.Chrome("./chromedriver")
# 구버전 네이버지도 접속
driver.get("https://map.kakao.com/")

# !!!추가//네이버 지도 업데이트 후 안내메시지 끄기##########
# 무시하고 진행해주세요.
# driver.find_elements_by_css_selector("button.btn_close")[1].click()
##################################################

my_client = MongoClient("")
ssafy = my_client['']
bigdata = ssafy['']
cnt = 0
search_box = driver.find_element_by_id("search.keyword.query")
search_button = driver.find_element_by_id("search.keyword.submit")

#gt, lt 모두 자신포함 안함 = 초과, 미만
# for i in bigdata.find({"id": {"$gt" : 10,"$lt": 20}}):
for i in bigdata.find({"id": {"$lt": 50001}}):

    print(cnt)
    cnt += 1

    try:
        # print(i[1])
        keyword = i['address'] + ' ' + i['name']
        num = i['id']
        print(keyword, num)
        search_box.send_keys(keyword) #검색어 입력
        time.sleep(1)
        search_box.send_keys(Keys.RETURN) #엔터키 입력
        time.sleep(1)
        driver.find_element_by_xpath("//*[@id=\"info.search.place.list\"]/li[1]/div[5]/div[4]/a[1]").send_keys(Keys.ENTER)#상세보기 클릭
        time.sleep(1)
        driver.switch_to.window(driver.window_handles[-1])#상세보기 창으로 전환
        time.sleep(2)
    except Exception as e:
        print(e)
        search_box.clear()
        continue
    try:
        one = driver.find_element_by_css_selector("#mArticle > div.cont_photo > div.photo_area > ul > li.size_l"
                                            ).get_attribute('innerHTML').split('"')[7][23:-2]
        # sql = f"insert into TourSightImages values ({num}, '{one}', null);"
        # print(sql)
        print(one)
        driver.close()#상세보기 창 닫기
        driver.switch_to.window(driver.window_handles[0])#원래창으로 복귀
        time.sleep(1)
        # print(image)
        search_box.clear()
    except Exception as e:
        print(e)
        driver.close()#상세보기 창 닫기
        driver.switch_to.window(driver.window_handles[0])#원래창으로 복귀
        time.sleep(1)
        # print(image)
        search_box.clear()
