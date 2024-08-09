public static String getLastWord(String queryString, int caretPosition) {
        if (caretPosition < 0)
            return "";

        // todo только ли пробелы здесь возможны?
        if (queryString.charAt(caretPosition) == ' ') {
            return "";
        }
        int lastWordStart = queryString.lastIndexOf(' ', caretPosition);
        String result = queryString.substring(lastWordStart + 1, caretPosition + 1);
        if (result.startsWith("in("))
            result = result.substring(3);

        return result;
    }