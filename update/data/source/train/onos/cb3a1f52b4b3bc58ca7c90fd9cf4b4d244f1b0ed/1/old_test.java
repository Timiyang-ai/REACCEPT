@Test
    public void getGetterForClassTest() {
        JavaAttributeInfo testAttr = getTestAttribute();
        String method = getGetterForClass(testAttr);
        assertThat(true, is(method.contains(PUBLIC + SPACE + STRING_DATA_TYPE + SPACE + GET_METHOD_PREFIX)));
    }