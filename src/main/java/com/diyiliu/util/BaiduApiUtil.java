package com.diyiliu.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: BaiduApiUtil
 * Author: DIYILIU
 * Update: 2016-04-29 16:49
 */
public class BaiduApiUtil {

    public static String decPosition(double lat, double lng) {

        String lat_lng = conv(lat, lng);
        String[] array = lat_lng.split(",");

        Map param = new HashMap() {
            {
                this.put("ak", "XVsrKFcXLk64BrOBnFchKNZo4CsxivrK");
                this.put("location", array[0] + "," + array[1]);
                this.put("output", "json");
            }
        };

        String url = "http://api.map.baidu.com/geocoder/v2/";

        try {
            String content = HttpclientUtil.askFor(url, "GET", param);
            Map rs = JacksonUtil.toObject(content, HashMap.class);

            if ((int) rs.get("status") == 0) {
                rs = (Map) rs.get("result");

                return (String) rs.get("formatted_address");
            }

            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String conv(double lat, double lng) {

        Map param = new HashMap() {
            {
                this.put("ak", "XVsrKFcXLk64BrOBnFchKNZo4CsxivrK");
                this.put("coords", lng + "," + lat);
                this.put("from", "1");
                this.put("to", "5");
                this.put("output", "json");
            }
        };

        String url = "http://api.map.baidu.com/geoconv/v1/";

        try {
            String content = HttpclientUtil.askFor(url, "GET", param);
            Map rs = JacksonUtil.toObject(content, HashMap.class);

            if ((int) rs.get("status") == 0) {

                List rsArray = (ArrayList) rs.get("result");

                rs = (Map) rsArray.get(0);

                return rs.get("y") + "," + rs.get("x");
            }

            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
