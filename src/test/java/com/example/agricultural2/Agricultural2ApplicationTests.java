package com.example.agricultural2;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Agricultural2ApplicationTests {

    @Test
    void contextLoads() {
   /* String msg="(060047295260BR00201109A3440.1357N11225.7403E000000621450160.901100000L00001F90P07BS226)";
    String msg1="(060047295260BP05863674042202661201107V000000000S0000000000W0000003031700000001100000LFFFFFFFFP07BS2200)";
      String z=  "863674042202661";
        System.out.println("imei"+msg1.substring(17,32));
        System.out.println("设备编号: "+msg.substring(1,13));
        System.out.println("设备编号: "+msg.substring(1,13));
        System.out.println("设备编号: "+msg.substring(1,13));
        System.out.println("纬度: "+msg.substring(24,33));
        System.out.println("经度: "+msg.substring(34,44));
        System.out.println("里程数: "+msg.substring(34,44));
        System.out.println("里程数: "+ Integer.parseInt(msg.substring(msg.indexOf("L")+1,msg.indexOf("L")+9),16));
*/
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
