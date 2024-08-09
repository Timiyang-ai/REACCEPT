@Test
    public void getGetterForClassTest() {
        JavaAttributeInfo testAttr = getTestAttribute();
        String method = getGetterForClass(testAttr, GENERATE_SERVICE_AND_MANAGER);
        assertThat(true, is(method.contains(PUBLIC + SPACE + STRING_DATA_TYPE + SPACE + GET_METHOD_PREFIX)));
    }