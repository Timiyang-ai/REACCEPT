public static String upperFirstLetter(String string) {
        if (isEmpty(string) || !Character.isLowerCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) - 32)) + string.substring(1);
    }