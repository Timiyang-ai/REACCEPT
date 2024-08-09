public static String getConstructor(String yangName, JavaAttributeInfo attr) {

        String attributeName = getSmallCase(attr.getAttributeName());

        String constructor = EIGHT_SPACE_INDENTATION + THIS + PERIOD + getCamelCase(attributeName, null) + SPACE + EQUAL
                + SPACE + BUILDER.toLowerCase() + OBJECT + PERIOD + GET_METHOD_PREFIX
                + getCaptialCase(getCamelCase(attributeName, null)) + OPEN_PARENTHESIS + CLOSE_PARENTHESIS + SEMI_COLAN
                + NEW_LINE;

        return constructor;
    }