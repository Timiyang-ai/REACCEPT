@Test
    public void recreateTokenFromBytes_TokenExpiredException() throws Exception {
        mock.checking(new Expectations() {
            {
                allowing(testTokenService).recreateTokenFromBytes(tokenBytes);
                one(testTokenService).recreateTokenFromBytes(tokenBytes, null);
                will(throwException(new TokenExpiredException("Expected test exception")));
            }
        });

        try {
            tokenManager.recreateTokenFromBytes(tokenBytes);
            fail("recreateTokenFromBytes should have throw an TokenExpiredException as per the mock setting");
        } catch (TokenExpiredException e) {
            // Success, we expect this exception type
        }

    }