public static String getConstructorStart(String yangName) {

        String javadoc = getConstructorString(yangName);
        String constructor = FOUR_SPACE_INDENTATION + PUBLIC + SPACE + yangName + IMPL + OPEN_PARENTHESIS + yangName
                + BUILDER + SPACE + BUILDER.toLowerCase() + OBJECT + CLOSE_PARENTHESIS + SPACE + OPEN_CURLY_BRACKET
                + NEW_LINE;
        return javadoc + constructor;
    }