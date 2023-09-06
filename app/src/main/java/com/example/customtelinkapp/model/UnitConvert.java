package com.example.customtelinkapp.model;

import com.telink.ble.mesh.util.MeshLogger;

import java.util.Calendar;

public final class UnitConvert {

    /**
     * @param lum 0-100
     * @return -32768-32767
     */
    public static int lum2level(int lum) {
        if (lum > 100) {
            lum = 100;
        }
        return (-32768 + (65535 * lum) / 100);
    }

    /**
     * @param level -32768-32767
     * @return lum 0-100
     */
    public static int level2lum(short level) {
        int lightness = level + 32768;
        int fix = 0;
        if (lightness != 0) {
            final int levelUnit = 65535 / 100 / 2;
            if (lightness < levelUnit + 2) {
                lightness = levelUnit * 2;
            }
            fix = levelUnit;
        }
        int re = (((lightness + fix) * 100) / 65535);
        MeshLogger.log("level2lum: " + level + " re: " + re);
        return re;
    }

    /**
     * @param lum 0-100
     * @return 0-65535
     */
    public static int lum2lightness(int lum) {
        return (int) Math.ceil((double) 65535 * lum / 100);
//        return 65535 * lum / 100;
    }

    /**
     * @param lightness 0-65535
     * @return lum 0-100
     */
    public static int lightness2lum(int lightness) {
//        return lightness * 100 / 65535;
        int fix = 0;
        if (lightness != 0) {
            final int levelUnit = 65535 / 100 / 2;
            if (lightness < levelUnit + 2) {
                lightness = levelUnit * 2;
            }
            fix = levelUnit;
        }
        int re = (((lightness + fix) * 100) / 65535);
        MeshLogger.log("lightness2lum: " + lightness + " re: " + re);
        return re;
    }

    /**
     * TEMP_MIN	 800
     * TEMP_MAX	 20000
     *
     * @param temp100 0-100
     * @return 800-2000
     */
    public static int temp100ToTemp(int temp100) {
        if (temp100 > 100) {
            temp100 = 100;
        }
        return (800 + ((20000 - 800) * temp100) / 100);
    }

    /**
     * @param temp 800-2000
     * @return temp100 0-100
     */
    public static int tempToTemp100(int temp) {
        if (temp < 800) return 0;
        if (temp > 20000) return 100;
        return (temp - 800) * 100 / (20000 - 800);
    }


    /**
     * get zone offset, 15min
     *
     * @return zoneOffSet
     */
    public static int getZoneOffset() {
        Calendar cal = Calendar.getInstance();
        // zone offset and daylight offset
        return (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / 60 / 1000 / 15 + 64;
    }
}
