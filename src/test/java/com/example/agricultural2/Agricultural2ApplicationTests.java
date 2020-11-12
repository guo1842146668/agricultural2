package com.example.agricultural2;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Agricultural2ApplicationTests {

    @Test
    void contextLoads() {
 /*       String lat = "11225.7460";
        String lng = "3440.1346";
        System.out.println(Double.parseDouble(lat.substring(0,lat.indexOf(".")-2))+Double.parseDouble(lat.substring(lat.indexOf(".")-2))/60);
*/
        String aa = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><status>1</status><info>ok</info><infocode>10000</infocode><locations>112.435189073351,34.668231879341</locations></response>";
        JSONObject jsonObject = XML.toJSONObject(aa);
        Object response = jsonObject.get("response");
        JSON parse = JSONUtil.parse(response);
        System.out.println(parse.getByPath("locations"));

       /* String lng2 = lng.substring(0,lng.indexOf(".")-2);
        String lng1 = lng.substring(lng.indexOf(".")-2);
        System.out.println(Double.parseDouble(lng2)+Double.parseDouble(lng1)/60);*/
    }


    private static String HexadecimalToDecimal(String ID){
        StringBuilder hexBuilder = new StringBuilder();
        String[] s = ID.split(" ");
        for (int i = 0; i < s.length-1; i++) {
            if(Integer.parseInt(s[i],16)<10){
                hexBuilder.append("0"+Integer.parseInt(s[i],16));
            }else{
                hexBuilder.append(Integer.parseInt(s[i],16));
            }
        }
        return hexBuilder.toString();
    }

}
