@Test
    public void getCamelCaseTest() {
        conflictResolver.setPrefixForIdentifier(null);
        String camelCase = getCamelCase(WITHOUT_CAMEL_CASE, conflictResolver);
        assertThat(camelCase.equals(WITH_CAMEL_CASE), is(true));
        String camelCase1 = getCamelCase(WITHOUT_CAMEL_CASE1, conflictResolver);
        assertThat(camelCase1.equals(WITH_CAMEL_CASE1), is(true));
        String camelCase2 = getCamelCase(WITHOUT_CAMEL_CASE2, conflictResolver);
        assertThat(camelCase2.equals(WITH_CAMEL_CASE2), is(true));
        String camelCase3 = getCamelCase(WITHOUT_CAMEL_CASE3, conflictResolver);
        assertThat(camelCase3.equals(WITH_CAMEL_CASE3), is(true));
        String camelCase4 = getCamelCase(WITHOUT_CAMEL_CASE4, conflictResolver);
        assertThat(camelCase4.equals(WITH_CAMEL_CASE4), is(true));
        String camelCase5 = getCamelCase(WITHOUT_CAMEL_CASE5, conflictResolver);
        assertThat(camelCase5.equals(WITH_CAMEL_CASE5), is(true));
        String camelCase6 = getCamelCase(WITHOUT_CAMEL_CASE6, conflictResolver);
        assertThat(camelCase6.equals(WITH_CAMEL_CASE6), is(true));
        String camelCase7 = getCamelCase(WITHOUT_CAMEL_CASE7, conflictResolver);
        assertThat(camelCase7.equals(WITH_CAMEL_CASE7), is(true));
        String camelCase8 = getCamelCase(WITHOUT_CAMEL_CASE8, conflictResolver);
        assertThat(camelCase8.equals(WITH_CAMEL_CASE8), is(true));
        String camelCase9 = getCamelCase(WITHOUT_CAMEL_CASE9, conflictResolver);
        assertThat(camelCase9.equals(WITH_CAMEL_CASE9), is(true));
        String camelCase10 = getCamelCase(WITHOUT_CAMEL_CASE10, conflictResolver);
        assertThat(camelCase10.equals(WITH_CAMEL_CASE10), is(true));
        String camelCase11 = getCamelCase(WITHOUT_CAMEL_CASE11, conflictResolver);
        assertThat(camelCase11.equals(WITH_CAMEL_CASE11), is(true));
        String camelCase12 = getCamelCase(WITHOUT_CAMEL_CASE12, conflictResolver);
        assertThat(camelCase12.equals(WITH_CAMEL_CASE12), is(true));
        String camelCase13 = getCamelCase(WITHOUT_CAMEL_CASE13, conflictResolver);
        assertThat(camelCase13.equals(WITH_CAMEL_CASE13), is(true));
        String camelCase14 = getCamelCase(WITHOUT_CAMEL_CASE14, conflictResolver);
        assertThat(camelCase14.equals(WITH_CAMEL_CASE14), is(true));
    }