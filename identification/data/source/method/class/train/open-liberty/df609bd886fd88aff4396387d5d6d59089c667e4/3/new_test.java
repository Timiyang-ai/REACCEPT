@Test
    public void test_getJavaScriptForRedirect_secureCookie() throws Exception {
        try {
            String requestUrlCookieName = "some cookie name";
            String redirectUrl = VALID_URL;

            mockery.checking(new Expectations() {
                {
                    one(webAppConfig).getSSORequiresSSL();
                    will(returnValue(true));
                }
            });

            String result = utils.getJavaScriptForRedirect(requestUrlCookieName, redirectUrl);

            Map<String, String> cookieProps = new HashMap<String, String>();
            cookieProps.put("path", "/");
            cookieProps.put("secure", null);

            verifyValidJavaScriptForRedirectBlock(result, requestUrlCookieName, redirectUrl, cookieProps);

        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }