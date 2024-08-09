@Test
    public void getConstructorTest() {
        JavaAttributeInfo testAttr = getTestAttribute();
        YangPluginConfig pluginConfig = new YangPluginConfig();
        String method = getConstructor(testAttr, GENERATE_SERVICE_AND_MANAGER, pluginConfig);
        assertThat(true, is(method.contains(THIS + PERIOD + CLASS_NAME + SPACE + EQUAL + SPACE + "builder" + OBJECT
                + PERIOD + GET_METHOD_PREFIX + "Testname" + OPEN_PARENTHESIS + CLOSE_PARENTHESIS + SEMI_COLAN)));
    }