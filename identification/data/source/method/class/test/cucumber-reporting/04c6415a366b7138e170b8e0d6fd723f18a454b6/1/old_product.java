public static String toValidFileName(String value) {
        return value.replaceAll("[^\\d\\w]", "-");
    }