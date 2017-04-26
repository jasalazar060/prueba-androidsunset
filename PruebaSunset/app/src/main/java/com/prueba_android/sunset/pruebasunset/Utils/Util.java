package com.prueba_android.sunset.pruebasunset.Utils;

/**
 * Created by ProgramaTuTambien on 25/04/2017.
 */

public class Util {

    public static boolean isNull(Object... object) {
        for (Object o : object) {
            if (null == o) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String... strings) {
        for (String o : strings) {
            if (isNull(o) || o.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
