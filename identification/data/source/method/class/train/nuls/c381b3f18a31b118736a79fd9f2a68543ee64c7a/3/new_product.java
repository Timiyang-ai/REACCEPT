public static boolean higherThan(String version0, String version1) {
        if (StringUtils.isBlank(version0) || StringUtils.isBlank(version1)) {
            throw new RuntimeException("version is null");
        }
        version0 = version0.replace("-SNAPSHOT", "");
        version1 = version1.replace("-SNAPSHOT", "");
        version0 = version0.replace("-BETA", "");
        version1 = version1.replace("-BETA", "");
        String[] array0 = version0.split("\\.");
        String[] array1 = version1.split("\\.");
        if (array0.length != 3 || array1.length != 3) {
            throw new RuntimeException("version format is wrong");
        }
        int[] intArr0 = strArrayToInt(array0);
        int[] intArr1 = strArrayToInt(array1);
        boolean result = intArr0[0] > intArr1[0];
        if (result) {
            return true;
        }
        result = intArr0[0] == intArr1[0] && intArr0[1] > intArr1[1];
        if (result) {
            return true;
        }
        return intArr0[0] == intArr1[0] && intArr0[1] == intArr1[1] && intArr0[2] > intArr1[2];
    }