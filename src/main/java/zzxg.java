import com.aliyun.odps.udf.UDF;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class zzxg extends UDF {

    //有过作战编号
    private static final String FIGHT_NUMBER = "fightnumber";

    //新规作战编号类型
    private static final String FIGHT_NUMBER_TYPE = "fightnumbertype";

    //过滤作战编号类型
    private static final String FILTER_FIGHT_NUMBER_TYPE = "filterfightnumbertype";

    //复活作战编号类型
    private static final String REVIVE_FIGHT_NUMBER_TYPE = "revivefightnumbertype";

    //周边作战次数
    private static final String CIRCUM_FIGHT_COUNT = "circumfightcount";

    //最后次周边作战距今天数
    private static final String END_CIRCUM_FIGHT_COUNT_DAYS = "endcircumfightcountdays";

    //最后次小红盒作战距今天数
    private static final String END_REDBOX_FIGHT_COUNT_DAYS = "endredboxfightcountdays";

    //最后次续订作战距今天数
    private static final String END_RENEW_FIGHT_COUNT_DAYS = "endrenewfightcountdays";

    public String evaluate(String fieldName, String value){
        if(fieldName == null || fieldName.isEmpty()) {
            return null;
        }

        if(value == null || value.isEmpty()) {
            return null;
        }

        String[] split = value.split(",");

        if(FIGHT_NUMBER.equals(fieldName)) {
            return fightNumber(split);
        }

        if(FIGHT_NUMBER_TYPE.equals(fieldName)) {
            return fightNumberType(split);
        }

        if(FILTER_FIGHT_NUMBER_TYPE.equals(fieldName)) {
            return filterFightNumberType(split);
        }

        if(REVIVE_FIGHT_NUMBER_TYPE.equals(fieldName)) {
            return reviveFightNumberType(split);
        }

        if(CIRCUM_FIGHT_COUNT.equals(fieldName)) {
            return circumFightCount(split);
        }

        if(END_CIRCUM_FIGHT_COUNT_DAYS.equals(fieldName)) {
            return endCircumFightCountDays(split);
        }

        if(END_REDBOX_FIGHT_COUNT_DAYS.equals(fieldName)) {
            return endRedboxFightCountDays(split);
        }

        if(END_RENEW_FIGHT_COUNT_DAYS.equals(fieldName)) {
            return endRenewFightCountDays(split);
        }

        return null;
    }

    public static String fightNumber(String[] arr) {
        List<String> ret = new ArrayList<>();
        if(fightNumberType(arr) != null) {
            ret.add("新规");
        }
        if(reviveFightNumberType(arr) != null) {
            ret.add("复活");
        }
        if(endRenewFightCountDays(arr) != null) {
            ret.add("续订");
        }
        if(circumFightCount(arr) != null) {
            ret.add("周边");
        }
        if(filterFightNumberType(arr) != null) {
            ret.add("过滤");
        }
        if(endRedboxFightCountDays(arr) != null) {
            ret.add("小红盒");
        }
        //新增标签值回访，开始位置为5 值为WI
        if(returnVisit(arr) != null) {
            ret.add("回访");
        }

        if(!ret.isEmpty()) {
            return StringUtils.join(ret.toArray(), ",");
        }
        return null;
    }
    //回访
    public static String returnVisit(String[] arr){
        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {
            if(campaign.indexOf("WI") == 4) {
                ret.add("WI");
            }

        }

        if(!ret.isEmpty()) {
            return StringUtils.join(ret.toArray(), ",");
        }

        return null;
    }

    public static String fightNumberType(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {
            if(campaign.indexOf("DM") == 4) {
                ret.add("DM");
            }
            if(campaign.indexOf("ZD") == 4) {
                ret.add("ZD");
            }
            if(campaign.indexOf("DZ") == 4) {
                ret.add("DZ");
            }
        }

        if(!ret.isEmpty()) {
            return StringUtils.join(ret.toArray(), ",");
        }

        return null;
    }

    public static String filterFightNumberType(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {
            if(campaign.indexOf("GL") == 4) {
                ret.add("GL");
            }
            if(campaign.indexOf("XH") == 4) {
                ret.add("XH");
            }
            if(campaign.indexOf("ZX") == 4) {
                ret.add("ZX");
            }
            if(campaign.indexOf("YLG") == 4) {
                ret.add("YLG(F)");
            }
            if(campaign.indexOf("ZZ") == 4) {
                ret.add("ZZ(F)");
            }
            if(campaign.indexOf("ZG") == 4) {
                ret.add("ZG(S)");
            }
            if(campaign.indexOf("XG") == 4) {
                ret.add("XG(S)");
            }
            if(campaign.indexOf("ZYL") == 4) {
                ret.add("ZYL(F)");
            }
        }

        if(!ret.isEmpty()) {
            return StringUtils.join(ret.toArray(), ",");
        }

        return null;
    }

    public static String reviveFightNumberType(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {
            if(campaign.indexOf("FX") == 4) {
                ret.add("FX");
            }
            if(campaign.indexOf("FHS") == 4) {
                ret.add("FHS");
            }
            if(campaign.indexOf("FHB") == 4) {
                ret.add("FHB");
            }
            if(campaign.indexOf("FHQ") == 4) {
                ret.add("FHQ");
            }
            if(campaign.indexOf("FHJ") == 4) {
                ret.add("FHJ");
            }
        }

        if(!ret.isEmpty()) {
            return StringUtils.join(ret.toArray(), ",");
        }

        return null;
    }

    public static String circumFightCount(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {

            for (int i = 1; i < 6; i++) {
                String v = "N" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(v);
                }
            }

            for (int i = 1; i < 6; i++) {
                String v = "P" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(v);
                }
            }

            for (int i=65; i<91;i++){
                char []upper = new char[26];
                upper[i-65] = (char)i;
                String v = "P" + upper[i-65];
                if(campaign.indexOf(v) == 4) {
                    ret.add(v);
                }
            }
            for (int i=65; i<91;i++){
                char []upper = new char[26];
                upper[i-65] = (char)i;
                String v = "Q" + upper[i-65];
                if(campaign.indexOf(v) == 4) {
                    ret.add(v);
                }
            }

            for (int i = 1; i < 6; i++) {
                String v = "Q" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(v);
                }
            }

            for (int i = 1; i < 6; i++) {
                String v = "Z" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(v);
                }
//                ret.add("ZY");
            }


            if(campaign.indexOf("ZY") == 4) {
                ret.add("ZY");
            }

//            String t = "ZY";
//            ret.add(t);
        }

        if(!ret.isEmpty()) {
            int l = ret.size();
            return l + "";
        }

        return null;
    }

    public static String endCircumFightCountDays(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {

            for (int i = 1; i < 6; i++) {
                String v = "N" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(campaign.substring(0, 4));
                }
            }

            for (int i = 1; i < 6; i++) {
                String v = "P" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(campaign.substring(0, 4));
                }
            }

            for (int i = 1; i < 6; i++) {
                String v = "Q" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(campaign.substring(0, 4));
                }
            }

            for (int i = 1; i < 6; i++) {
                String v = "Z" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(campaign.substring(0, 4));
                }
            }
        }

        if(!ret.isEmpty()) {
            int max = 0;
            String maxStr = "";
            for (String string : ret) {
                try {
                    int parseInt = Integer.parseInt(string);
                    if(parseInt > max) {
                        max = parseInt;
                        maxStr = string;
                    }
                } catch (Exception e) {
                }
            }

            if(!maxStr.isEmpty()) {
                Integer month = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c1.setTime(sdf.parse("20" + maxStr));
                    int month1 = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
                    int month2 = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
                    month = Math.abs(month1 + month2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(month != null) {
                    return month + "";
                }
            }
        }

        return null;
    }

    public static String endRedboxFightCountDays(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {

            for (int i = 1; i < 6; i++) {
                String v = "H" + i;
                if(campaign.indexOf(v) == 4) {
                    ret.add(campaign.substring(0, 4));
                }
            }
        }

        if(!ret.isEmpty()) {
            int max = 0;
            String maxStr = "";
            for (String string : ret) {
                try {
                    int parseInt = Integer.parseInt(string);
                    if(parseInt > max) {
                        max = parseInt;
                        maxStr = string;
                    }
                } catch (Exception e) {
                }
            }

            if(!maxStr.isEmpty()) {
                Integer month = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c1.setTime(sdf.parse("20" + maxStr));
                    int month1 = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
                    int month2 = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
                    month = Math.abs(month1 + month2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(month != null) {
                    return month + "";
                }
            }
        }

        return null;
    }

    public static String endRenewFightCountDays(String[] arr) {

        List<String> ret = new ArrayList<>();
        for (String campaign : arr) {
            if(campaign.indexOf("DQ") == 4) {
                ret.add(campaign.substring(0, 4));
            }

            if(campaign.indexOf("DG") == 4) {
                ret.add(campaign.substring(0, 4));
            }
            if(campaign.indexOf("DS") == 4) {
                ret.add(campaign.substring(0, 4));
            }
            if(campaign.indexOf("DF") == 4) {
                ret.add(campaign.substring(0, 4));
            }
            if(campaign.indexOf("DK") == 4) {
                ret.add(campaign.substring(0, 4));
            }
        }

        if(!ret.isEmpty()) {
            int max = 0;
            String maxStr = "";
            for (String string : ret) {
                try {
                    int parseInt = Integer.parseInt(string);
                    if(parseInt > max) {
                        max = parseInt;
                        maxStr = string;
                    }
                } catch (Exception e) {
                }
            }

            if(!maxStr.isEmpty()) {
                Integer month = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c1.setTime(sdf.parse("20" + maxStr));
                    int month1 = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
                    int month2 = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
                    month = Math.abs(month1 + month2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(month != null) {
                    return month + "";
                }
            }
        }

        return null;
    }
}