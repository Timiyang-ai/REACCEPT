public static String toValidFileName(String fileName) {
        return StringEscapeUtils.escapeJava(fileName).replaceAll("[^\\d\\w]", "-");
    }