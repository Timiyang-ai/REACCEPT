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
        String camelCase5 = getCamelCase(WITHOUT_CAMEL_CASE5, null);
        assertThat(camelCase5.equals(WITH_CAMEL_CASE5), is(true));
        String camelCase6 = getCamelCase(WITHOUT_CAMEL_CASE6, null);
        assertThat(camelCase6.equals(WITH_CAMEL_CASE6), is(true));
        String camelCase7 = getCamelCase(WITHOUT_CAMEL_CASE7, null);
        assertThat(camelCase7.equals(WITH_CAMEL_CASE7), is(true));
        String camelCase8 = getCamelCase(WITHOUT_CAMEL_CASE8, null);
        assertThat(camelCase8.equals(WITH_CAMEL_CASE8), is(true));
        String camelCase9 = getCamelCase(WITHOUT_CAMEL_CASE9, null);
        assertThat(camelCase9.equals(WITH_CAMEL_CASE9), is(true));
        String camelCase10 = getCamelCase(WITHOUT_CAMEL_CASE10, null);
        assertThat(camelCase10.equals(WITH_CAMEL_CASE10), is(true));
        String camelCase11 = getCamelCase(WITHOUT_CAMEL_CASE11, null);
        assertThat(camelCase11.equals(WITH_CAMEL_CASE11), is(true));
        String camelCase12 = getCamelCase(WITHOUT_CAMEL_CASE12, null);
        assertThat(camelCase12.equals(WITH_CAMEL_CASE12), is(true));
    }