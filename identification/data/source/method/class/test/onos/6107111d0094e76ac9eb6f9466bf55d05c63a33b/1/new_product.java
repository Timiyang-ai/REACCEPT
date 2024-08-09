public static String getConstructor(String yangName, JavaAttributeInfo attr, int generatedJavaFiles,
            YangPluginConfig pluginConfig) {

        String attributeName = attr.getAttributeName();
        String constructor;

        if ((generatedJavaFiles & GENERATE_SERVICE_AND_MANAGER) != 0) {
            constructor =
                    EIGHT_SPACE_INDENTATION + THIS + PERIOD
                            + getCamelCase(attributeName, pluginConfig.getConflictResolver()) + SPACE + EQUAL
                            + SPACE + BUILDER.toLowerCase() + OBJECT + PERIOD + GET_METHOD_PREFIX
                            + getCapitalCase(getCamelCase(attributeName, pluginConfig.getConflictResolver()))
                            + OPEN_PARENTHESIS + CLOSE_PARENTHESIS + SEMI_COLAN + NEW_LINE;
        } else {
            constructor =
                    EIGHT_SPACE_INDENTATION + THIS + PERIOD
                            + getCamelCase(attributeName, pluginConfig.getConflictResolver()) + SPACE + EQUAL
                            + SPACE + BUILDER.toLowerCase() + OBJECT + PERIOD
                            + getCamelCase(attributeName, pluginConfig.getConflictResolver()) +
                            OPEN_PARENTHESIS + CLOSE_PARENTHESIS + SEMI_COLAN + NEW_LINE;
        }
        return constructor;
    }