/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class DateHelper {

    public static String Now() {
        SimpleDateFormat timenow = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        return day.format((new java.util.Date())) + "/"
                + month.format((new java.util.Date())) + "/"
                + year.format((new java.util.Date())) + " "
                + timenow.format((new java.util.Date()));
    }

    public static String Now_Ver2() {
        SimpleDateFormat timenow = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        String timestamp = year.format((new java.util.Date())) + "-"
                + month.format((new java.util.Date())) + "-"
                + day.format((new java.util.Date())) + " "
                + timenow.format((new java.util.Date()));
        return timestamp;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(date);
    }

    public static int compare(Timestamp t1, Timestamp t2) {
        long l1 = t1.getTime();
        long l2 = t2.getTime();
        if (l2 > l1) {
            return 1;
        } else if (l1 > l2) {
            return -1;
        } else {
            return 0;
        }
    }

    public static boolean compare(String ts) {
        Timestamp ts1 = Timestamp.valueOf(ts);
        Timestamp ts2 = Timestamp.valueOf(DateHelper.Now_Ver2());
        int minutes = ts2.getMinutes() - ts1.getMinutes();
        if (ts1.getHours() == ts2.getHours()) {   // nếu thời gian bằng nhau
            if (minutes >= 0 && minutes <= 10) { // so sánh phút nếu 
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        SimpleDateFormat timenow = new SimpleDateFormat("HH:mm:ss");
//        SimpleDateFormat day = new SimpleDateFormat("dd");
//        SimpleDateFormat month = new SimpleDateFormat("MM");
//        SimpleDateFormat year = new SimpleDateFormat("yyyy");
//        String timestamp = year.format((new java.util.Date())) + "-"
//                + month.format((new java.util.Date())) + "-"
//                + day.format((new java.util.Date())) + " "
//                + timenow.format((new java.util.Date()));
//        System.out.println(timestamp);
//
//        Timestamp ts1 = Timestamp.valueOf(timestamp);
//        Timestamp ts2 = Timestamp.valueOf(DateHelper.Now_Ver2());
        System.out.println(DateHelper.compare("2021-12-06 16:24:00"));
    }
}
