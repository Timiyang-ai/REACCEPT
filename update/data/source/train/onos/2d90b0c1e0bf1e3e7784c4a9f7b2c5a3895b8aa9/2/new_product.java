static String getConstructorStart(String yangName, YangPluginConfig pluginConfig, boolean isRootNode) {

        String javadoc = getConstructorString(yangName, pluginConfig);

        String returnType = getCapitalCase(DEFAULT) + yangName;
        if (isRootNode) {
            returnType = yangName + OP_PARAM;
        }
        String constructor = FOUR_SPACE_INDENTATION + PUBLIC + SPACE + returnType +
                OPEN_PARENTHESIS + yangName + BUILDER + SPACE + BUILDER.toLowerCase() + OBJECT
                + CLOSE_PARENTHESIS + SPACE + OPEN_CURLY_BRACKET + NEW_LINE;
        return javadoc + constructor;
    }