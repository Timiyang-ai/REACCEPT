@Test
    public void getConstructorTest() {
        JavaAttributeInfo testAttr = getTestAttribute();
        String method = getConstructor(CLASS_NAME, testAttr);
        assertThat(true, is(method.contains(THIS + PERIOD + CLASS_NAME + SPACE + EQUAL + SPACE + "builder" + OBJECT
                + PERIOD + GET_METHOD_PREFIX + "Testname" + OPEN_PARENTHESIS + CLOSE_PARENTHESIS + SEMI_COLAN)));
    }