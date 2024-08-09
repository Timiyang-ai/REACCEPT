@Test
    public void recreateTokenFromBytes_TokenExpiredException() throws Exception {
        mock.checking(new Expectations() {
            {
                one(testTokenService).recreateTokenFromBytes(tokenBytes, removeAttrs);
                will(throwException(new TokenExpiredException("Expected test exception")));
                one(testTokenService).recreateTokenFromBytes(tokenBytes);
                will(throwException(new TokenExpiredException("Expected test exception")));
            }
        });

        try {
            tokenManager.recreateTokenFromBytes(tokenBytes);
            fail("recreateTokenFromBytes should have throw an TokenExpiredException as per the mock setting");
            tokenManager.recreateTokenFromBytes(tokenBytes, removeAttrs);
            fail("recreateTokenFromBytes should have throw an TokenExpiredException as per the mock setting");
        } catch (TokenExpiredException e) {
            // Success, we expect this exception type
        }

    }