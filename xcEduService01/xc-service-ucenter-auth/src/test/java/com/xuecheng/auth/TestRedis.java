package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        //定义key
        String key = "user_token:0368d9af-d86c-42b6-80aa-6b66ac56174e";
        //定义value
        Map<String,String> value = new HashMap<>();
        value.put("jwt","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU0NTQxNjExNCwianRpIjoiMDM2OGQ5YWYtZDg2Yy00MmI2LTgwYWEtNmI2NmFjNTYxNzRlIiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.WhqzkN7K44-0KVj9NLDpzI3LEnjfAwenUc_iHyaydgZ0TgmtZZaHziJF1e4Hq7Z4c9dy9bALVM16uSenHqMYEuo3xjeQ-ca_T2jIF-GEKBVwbOxLXAkZNAJ9ZJcyqnu3Dz3x_V1ph74oLODM4c1Rc0JeataQcjxHBN63mkSs2NdNw1IOQCUCWIUe-8AdrElWOnZBkf2LC2UoU8pRGjQhe6JNo9LvKCmYYoPiKqeqj_uSZggBHQhAyOmV136r5YyAPo0Ig6BmqddeCVNyyaXsA51AhwSuRQ30IgYptxM06WjPo2f6bEehP6_HPF372qae7LPFNFHB6GTPLGOAmi8GnQ");
        value.put("refresh_token","eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOm51bGwsInVzZXJwaWMiOm51bGwsInVzZXJfbmFtZSI6Iml0Y2FzdCIsInNjb3BlIjpbImFwcCJdLCJhdGkiOiIwMzY4ZDlhZi1kODZjLTQyYjYtODBhYS02YjY2YWM1NjE3NGUiLCJuYW1lIjpudWxsLCJ1dHlwZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU0NTQxNjExNCwianRpIjoiODhjYmQ3N2UtNDEyNi00MmIxLTkzYjEtNjczMThmZWFlZjJkIiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.ezo5zZy1Z1kZnVvy3SXZJLDh-bK2mhP0zxu27QqYKFND9clafuqHbsGC3J0cc_YnozmlGRGOuJ9LvRfZ_HnZROq2qTxZPP6M6Fq0CJY0PGf1RVfbsoNRuTe6WFZeN97hC_Jqx8aJz3slEHc90jtt2n-I6pteP7FT0ZNwX2uTFpvXUZSE8jSW6mKmUQz0Bq7JJLIAmbU1erIkEI2fOCPtR6nrzXHO6J5Ue67rIqJqCXuwM1mj5LhzQT7vBIQxM9qjDwfiFpMEb4vpEUGG2UGq4qub4ru_cMpqg75mROouDWCmrI9HFT_N2HopL3Pq74eBirZnC-ujwXPwK3HvScdwDw");
        String jsonString = JSON.toJSONString(value);
        //校验key是否存在
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        System.out.println(expire);
        //存储数据
        stringRedisTemplate.boundValueOps(key).set(jsonString,30, TimeUnit.SECONDS);
        //获取数据
        String string = stringRedisTemplate.opsForValue().get(key);
        System.out.println(string);
    }

}


















