public static String getLastWord(String queryString, int caretPosition) {
        if (caretPosition < 0)
            return "";

        if (Character.isSpaceChar(queryString.charAt(caretPosition))) {
            return "";
        }

        String[] words = queryString.substring(0, caretPosition + 1).split("\\s");
        String result = words[words.length - 1];

        if (StringUtils.isBlank(result)) {
            return result;
        }

        int leftBracketsIdx = result.lastIndexOf('(');
        if (leftBracketsIdx >= 0 && leftBracketsIdx < result.length()) {
            return result.substring(leftBracketsIdx + 1);
        }

        if (!result.contains("'")) {
            int operationIdx = StringUtils.lastIndexOfAny(result, ARITHMETIC_OPERATIONS);
            if (operationIdx >= 0 && operationIdx < result.length()) {
                return result.substring(operationIdx + 1);
            }
        }

        return result;
    }