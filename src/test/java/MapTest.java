import com.diyiliu.util.BaiduApiUtil;
import com.diyiliu.util.HttpclientUtil;
import com.diyiliu.util.JacksonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: MapTest
 * Author: DIYILIU
 * Update: 2016-04-29 16:05
 */
public class MapTest {

    @Test
    public void test() throws Exception {

        Map map = new HashMap() {
            {
                this.put("ak", "XVsrKFcXLk64BrOBnFchKNZo4CsxivrK");
                this.put("location", "34.211811,117.337585");
                this.put("output", "json");
                //this.put("pois", 1);
            }
        };

        System.out.println(HttpclientUtil.askFor("http://api.map.baidu.com/geocoder/v2/", "GET", map));
    }

    @Test
    public void test1() throws Exception {

        Map map = new HashMap() {
            {
                this.put("ak", "XVsrKFcXLk64BrOBnFchKNZo4CsxivrK");
                this.put("coords", "");
                this.put("from", "1");
                this.put("to", "5");
                this.put("output", "json");
            }
        };

        String content = HttpclientUtil.askFor("http://api.map.baidu.com/geoconv/v1/", "GET", map);

        System.out.println(content);

        Map rs = JacksonUtil.toObject(content, HashMap.class);

        System.out.println(rs.get("status"));
    }

    @Test
    public void test2() {

        String rs = BaiduApiUtil.decPosition(34.211811, 117.337585);

        System.out.println(rs);
    }
}
