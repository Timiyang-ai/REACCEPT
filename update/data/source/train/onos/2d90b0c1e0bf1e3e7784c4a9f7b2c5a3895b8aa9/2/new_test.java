@Test
    public void getConstructorStartTest() {
        YangPluginConfig pluginConfig = new YangPluginConfig();
        String method = getConstructorStart(CLASS_NAME, pluginConfig, false);
        assertThat(true, is(method.contains(PUBLIC + SPACE + "Default" + CLASS_NAME + OPEN_PARENTHESIS + CLASS_NAME
                + BUILDER + SPACE + BUILDER.toLowerCase() + OBJECT + CLOSE_PARENTHESIS + SPACE
                + OPEN_CURLY_BRACKET + NEW_LINE)));
    }