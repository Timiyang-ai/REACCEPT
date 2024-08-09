@Test
    public void getGetterForInterfaceTest() {
        String method = getGetterForInterface(CLASS_NAME, STRING_DATA_TYPE, false);
        assertThat(true, is(method.contains(STRING_DATA_TYPE + SPACE + GET_METHOD_PREFIX)));
    }