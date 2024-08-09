@Test
    public void getSetterForClassTest() {
        JavaAttributeInfo testAttr = getTestAttribute();
        String method = getSetterForClass(testAttr, CLASS_NAME, GENERATE_SERVICE_AND_MANAGER);
//        assertThat(true, is(
//                method.contains(PUBLIC + SPACE + CLASS_NAME + BUILDER + SPACE + SET_METHOD_PREFIX
//                        + getCaptialCase(ATTRIBUTE_NAME) + OPEN_PARENTHESIS + STRING_DATA_TYPE + SPACE
//                        + ATTRIBUTE_NAME)));
    }