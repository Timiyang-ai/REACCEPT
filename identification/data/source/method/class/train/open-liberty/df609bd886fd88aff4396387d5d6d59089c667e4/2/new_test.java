@Test
    public void test_getUnencodedJavaScriptHtmlCookieString_htmlCharsInName_htmlCharsInValue() throws Exception {
        try {
            String name = "<cookie name\"'>";
            String value = ">cookie'<\" value ";

            String result = utils.getUnencodedJavaScriptHtmlCookieString(name, value);

            String expectedCookieString = name + "=" + value + ";";
            verifyCaseInsensitiveQuotedPatternMatches(result, DOCUMENT_COOKIE_START + expectedCookieString + DOCUMENT_COOKIE_END, "Cookie string did not match expected pattern.");

        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }