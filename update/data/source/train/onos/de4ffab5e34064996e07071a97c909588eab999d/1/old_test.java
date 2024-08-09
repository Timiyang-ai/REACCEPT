@Test
    public void getCamelCaseTest() {
        String camelCase = getCamelCase(WITHOUT_CAMEL_CASE);
        assertThat(camelCase.equals(WITH_CAMEL_CASE), is(true));
    }