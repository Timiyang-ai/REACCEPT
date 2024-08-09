@Test
    public void test_getUnencodedJavaScriptHtmlCookieString_emptyCookieProperties() throws Exception {
        try {
            String name = "<cookie name\"'>";
            String value = ">cookie'<\" value ";
            Map<String, String> cookieProps = new HashMap<String, String>();

            String result = utils.getUnencodedJavaScriptHtmlCookieString(name, value, cookieProps);

            String expectedCookieString = DOCUMENT_COOKIE_START + name + "=" + value + ";";
            verifyPattern(result, Pattern.quote(expectedCookieString), "Expected cookie name and value did not appear in the result.");

        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }