public static boolean higherThan(String version0, String version1) {
        if (StringUtils.isBlank(version0) || StringUtils.isBlank(version1)) {
            throw new RuntimeException("version is null");
        }
        Integer[] intArr0 = strArrayToInt(version0);
        Integer[] intArr1 = strArrayToInt(version1);
        boolean result = false;
        for (int i = 0; i < intArr0.length; i++) {
            Integer val1 = intArr0[i];
            if (intArr1.length <= i && val1 > 0) {
                result = true;
                break;
            }
            Integer val2 = intArr1[i];
            if (val1 > val2) {
                result = true;
                break;
            }
        }
        //当version1版本号位数更多时
        if (!result && intArr1.length > intArr0.length) {
            result = intArr1[intArr0.length] > 0;
        }
        return result;
    }