public static String getPackageName(String className) {
        if (StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("The class name must not be empty");
        }
        int i = className.lastIndexOf('.');
        if (i == -1) {
            return "";
        }
        return className.substring(0, i);
    }