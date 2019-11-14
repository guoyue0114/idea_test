import com.aliyun.odps.udf.UDF;
/*
 *
 *功能描述
        * @author jiawei
        * @date 2019-11-12
 * @param null
        * @return
*/
public class StringDate extends UDF {     
    /* 
     * @Description //TODO 
     * @Date 2019-11-12 18:10
     * @Param [s, f]      
     * @return java.lang.String
     **/                                  
    public String evaluate(String s,String f) {
        /* * @Description //TODO 
         * @Date 2019-11-12 17:39
         * @Param [s, f]      
         * @return java.lang.String       
         **/           
        if (s != null) {
            String[] crtdt = s.split(f);
            try {
                int a = Integer.valueOf(crtdt[1]);
                int b = Integer.valueOf(crtdt[2]);
                if (a < 10) {
                    crtdt[1] = 0 + crtdt[1];
                }

                if (b < 10) {
                    crtdt[2] = 0 + crtdt[2];
                }
                String c = crtdt[0] + crtdt[1] + crtdt[2];
                return c;
            } catch (Exception e) {
                System.out.println("数据格式有误");
                return s;
            }
        }
        return null;
    }
}