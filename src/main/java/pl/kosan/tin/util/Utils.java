package pl.kosan.tin.util;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String cryptPass(String password) {

        String pass="";
        char[] tab = password.toCharArray();
        List list =  new ArrayList();

        for (char x : tab)
            pass+=String.valueOf((int) x);

        return pass;
    }

}
