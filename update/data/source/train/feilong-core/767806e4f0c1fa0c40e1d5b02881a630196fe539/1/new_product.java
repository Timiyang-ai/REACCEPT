public static String substringWithoutLast(final CharSequence text,final String lastString){
        if (null == text){
            return StringUtils.EMPTY;
        }

        String textString = text.toString();
        if (null == lastString){
            return textString;
        }
        return textString.endsWith(lastString) ? substringWithoutLast(textString, lastString.length()) : textString;
    }