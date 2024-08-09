@Test
    public void createSSOToken_TokenCreationFailedException() throws Exception {
        mock.checking(new Expectations() {
            {
                one(testTokenService).createToken(testTokenData);
                will(throwException(new TokenCreationFailedException("Expected test exception")));
            }
        });
        try {
            tokenManager.createToken(TEST_TOKEN_TYPE, testTokenData);
            fail("createToken should have throw an TokenCreationFailedException as per the mock setting");
        } catch (TokenCreationFailedException e) {
            // Success, we expect this exception type
        }
    }