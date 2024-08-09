public static String getShortClassName(String className) {
        if (StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("The class name must not be empty");
        }
        char[] chars = className.toCharArray();
        int lastDot = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == PACKAGE_SEPARATOR_CHAR) {
                lastDot = i + 1;
            } else if (chars[i] == INNER_CLASS_SEPARATOR_CHAR) {  // handle inner classes
                chars[i] = PACKAGE_SEPARATOR_CHAR;
            }
        }
        return new String(chars, lastDot, chars.length - lastDot);
    }