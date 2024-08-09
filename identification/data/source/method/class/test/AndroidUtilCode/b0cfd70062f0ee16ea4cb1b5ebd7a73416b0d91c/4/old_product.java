public static String lowerFirstLetter(String string) {
        if (isEmpty(string) || !Character.isUpperCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) + 32)) + string.substring(1);
    }