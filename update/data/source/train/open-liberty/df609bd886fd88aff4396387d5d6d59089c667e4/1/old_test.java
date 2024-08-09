@Test
    public void test_getHtmlCookieString_nullPropertyMap() throws Exception {
        try {
            String name = "some name";
            String value = "a value";
            Map<String, String> cookieProps = null;

            String result = utils.getHtmlCookieString(name, value, cookieProps);

            String expectedCookieString = name + "=" + value + ";";
            verifyCaseInsensitiveQuotedPatternMatches(result, expectedCookieString, "Cookie string did not match expected pattern.");

        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }