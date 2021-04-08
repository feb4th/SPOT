from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import pymysql
import pandas as pd
import time

db = pymysql.connect(
    user='',
    passwd='',
    host='',
    db='',
    charset=''
)

cursor = db.cursor()
sql = "SELECT id ,name FROM TourSight"
cursor.execute(sql)
result = cursor.fetchall()
# result = pd.DataFrame(result)
# print(result[1])

driver = webdriver.Chrome("./chromedriver")
# 네이버이미지 접속
driver.get("https://search.naver.com/search.naver?sm=tab_hty.top&where=image&query=%EC%A4%91%EB%9E%91%EC%BA%A0%ED%95%91%EC%88%B2&oquery=d&tqi=hb36OwprvTossSfoOdZsssssthN-453325")

# driver.find_elements_by_css_selector("button.btn_close")[1].click()
##################################################
search_box = driver.find_element_by_id("nx_query")##검색창
search_button = driver.find_element_by_class_name("bt_search")##검색버튼
# pd.set_option('display.max_rows', None)
search_box.clear()
for i in result:
    try:
        # 검색창에 검색어 입력하기 // 검색창: input#search-input
        print(i[1])
        keyword = i[1]
        num = i[0]
        # search_box.send_keys(keyword) #검색어 입력
        driver.find_element_by_id("nx_query").send_keys(keyword)
        time.sleep(1)
        # search_box.send_keys(Keys.RETURN) #엔터키 입력
        driver.find_element_by_class_name("bt_search").send_keys(Keys.RETURN)
        time.sleep(1)
        # driver.find_element_by_xpath("//*[@id=\"info.search.place.list\"]/li[1]/div[5]/div[4]/a[1]").send_keys(Keys.ENTER)#상세보기 클릭
        # time.sleep(1)
        # driver.switch_to.window(driver.window_handles[-1])#상세보기 창으로 전환
        # time.sleep(1)
    except Exception as e:
        print(e)
        # search_box.clear()
        driver.find_element_by_id("nx_query").clear()
        continue

    # print(driver.find_element_by_css_selector("#mArticle > div.cont_photo.no_category > div.photo_area > ul > li.size_l")
    #       .get_attribute('innerHTML').split('"')[7][23:-2])

    try:

        one = driver.find_element_by_css_selector("#main_pack > section > div > div.photo_group._listGrid > div.photo_tile._grid > div:nth-child(1) > div > div.thumb > a"
                                            ).get_attribute('innerHTML').split('"')[1]
        sql = f"insert into TourSightImages values ({num}, '{one}', null);"
        # print(sql)
        print(one)

        cursor.execute(sql)
        # two = driver.find_element_by_css_selector("#main_pack > section > div > div.photo_group._listGrid > div.photo_tile._grid > div:nth-child(1) > div > div.thumb > a >"
        #                                     ).get_attribute('innerHTML').split('"')[1]
        # sql = f"insert into TourSightImages values ({num}, '{two}', null);"
        # cursor.execute(sql)
        # three = driver.find_element_by_css_selector("#mArticle > div.cont_photo.no_category > div.photo_area > ul > li:nth-child(3)"
        #                                     ).get_attribute('innerHTML').split('"')[7][23:-2]
        # sql = f"insert into TourSightImages values ({num}, '{three}', null);"
        # cursor.execute(sql)
        # four = driver.find_element_by_css_selector("#mArticle > div.cont_photo.no_category > div.photo_area > ul > li:nth-child(4)"
        #                                     ).get_attribute('innerHTML').split('"')[7][23:-2]
        #
        # sql = f"insert into TourSightImages values ({num}, '{four}', null);"
        # cursor.execute(sql)
        # five = driver.find_element_by_css_selector("#mArticle > div.cont_photo.no_category > div.photo_area > ul > li:nth-child(5)"
        #                                     ).get_attribute('innerHTML').split('"')[5][23:-2]
        # sql = f"insert into TourSightImages values ({num}, '{five}', null);"
        # cursor.execute(sql)

        # driver.close()#상세보기 창 닫기
        # driver.switch_to.window(driver.window_handles[0])#원래창으로 복귀
        time.sleep(1)
        # print(image)
        # search_box.clear()
        driver.find_element_by_id("nx_query").clear()
        time.sleep(1)
        db.commit()
    except Exception as e:
        print(e)
        # driver.close()#상세보기 창 닫기
        # driver.switch_to.window(driver.window_handles[0])#원래창으로 복귀
        # time.sleep(2)
        # print(image)
        # search_box.clear()
        driver.find_element_by_id("nx_query").clear()

db.commit()
db.close()


# search_box.send_keys('햄버거')
# time.sleep(2)
# search_button.click()
# search_box.clear()
# search_box.send_keys('피자')
# time.sleep(2)
# search_button.click()

