public static String toValidFileName(String value) {
        return StringEscapeUtils.escapeJava(value).replaceAll("[^\\d\\w]", "-");
    }