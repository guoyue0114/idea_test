import java.text.DateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {

        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM); //省略了Locale对象，默认中文环境
        String dateStr = df.format(date);
        System.out.println(dateStr);

    }
}
