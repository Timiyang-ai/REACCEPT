public static String getLastWord(String queryString, int caretPosition) {
        if (caretPosition < 0)
            return "";

        if (Character.isSpaceChar(queryString.charAt(caretPosition))) {
            return "";
        }

        String[] words = queryString.substring(0, caretPosition + 1).split("\\s");
        String result = words[words.length - 1];

        if (result.startsWith("in("))
            result = result.substring(3);

        return result;
    }