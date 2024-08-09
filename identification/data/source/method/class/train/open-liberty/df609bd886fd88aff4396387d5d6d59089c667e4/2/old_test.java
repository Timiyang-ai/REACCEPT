@Test
    public void test_getJavaScriptHtmlCookieString_withCookieSettings() throws Exception {
        try {
            utils = getUtilsWithHtmlCookieStringMethodMocked();

            String name = "some value";
            String value = "some value";
            Map<String, String> cookieProps = new HashMap<String, String>();
            cookieProps.put("key1", "value1");
            cookieProps.put("key2", "value2");

            final String mockedCookieString = name + "=" + value + "; key1=value1; key2=value2";
            setMockedCookieStringExpectation(mockedCookieString);

            String result = utils.getJavaScriptHtmlCookieString(name, value, cookieProps);

            verifyCaseInsensitiveQuotedPatternMatches(result, DOCUMENT_COOKIE_START + mockedCookieString + DOCUMENT_COOKIE_END, "Cookie string did not match expected pattern.");

        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }