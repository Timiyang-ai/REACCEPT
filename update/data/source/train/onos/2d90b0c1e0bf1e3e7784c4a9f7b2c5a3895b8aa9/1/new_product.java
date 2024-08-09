static String getBuild(String yangName, boolean isRootNode) {
        String type = getCapitalCase(DEFAULT) + yangName;
        if (isRootNode) {
            type = yangName + OP_PARAM;
        }
        return FOUR_SPACE_INDENTATION + PUBLIC + SPACE + yangName + SPACE + BUILD + OPEN_PARENTHESIS +
                CLOSE_PARENTHESIS
                + SPACE + OPEN_CURLY_BRACKET + NEW_LINE + EIGHT_SPACE_INDENTATION + RETURN + SPACE + NEW + SPACE +
                type + OPEN_PARENTHESIS + THIS + CLOSE_PARENTHESIS + SEMI_COLAN
                + NEW_LINE + FOUR_SPACE_INDENTATION + CLOSE_CURLY_BRACKET;
    }