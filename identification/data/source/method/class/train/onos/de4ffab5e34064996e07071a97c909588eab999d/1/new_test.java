@Test
    public void getCamelCaseTest() {
        String camelCase = getCamelCase(WITHOUT_CAMEL_CASE, null);
        assertThat(camelCase.equals(WITH_CAMEL_CASE), is(true));
        String camelCase1 = getCamelCase(WITHOUT_CAMEL_CASE1, null);
        assertThat(camelCase1.equals(WITH_CAMEL_CASE1), is(true));
        String camelCase2 = getCamelCase(WITHOUT_CAMEL_CASE2, null);
        assertThat(camelCase2.equals(WITH_CAMEL_CASE2), is(true));
        String camelCase3 = getCamelCase(WITHOUT_CAMEL_CASE3, null);
        assertThat(camelCase3.equals(WITH_CAMEL_CASE3), is(true));
        String camelCase4 = getCamelCase(WITHOUT_CAMEL_CASE4, null);
        assertThat(camelCase4.equals(WITH_CAMEL_CASE4), is(true));
    }