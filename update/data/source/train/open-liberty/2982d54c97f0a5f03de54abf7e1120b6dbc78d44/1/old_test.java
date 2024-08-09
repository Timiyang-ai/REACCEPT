@Test
    public void test_readJsonFromContent_jsonSpansMultipleLines() {
        try {
            UserInfoJsonExpectation exp = new UserInfoJsonExpectation(KEY_TO_LOOK_FOR);

            final String content = UserInfoJsonExpectation.SERVLET_OUTPUT_PREFIX + "{\n\r\"key\":\t \n\"value\"\r }";
            mockery.checking(new Expectations() {
                {
                    one(webResponse).getContentAsString();
                    will(returnValue(content));
                }
            });
            try {
                JsonObject result = exp.readJsonFromContent(webResponse);
                fail("Should have thrown an exception but found JSON data: " + result);
            } catch (Exception e) {
                verifyException(e, FAILURE_REGEX_ERROR_READING_JSON_CONTENT + "Did not find.*" + Pattern.quote(UserInfoJsonExpectation.USER_INFO_SERVLET_OUTPUT_REGEX));
            }
        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }