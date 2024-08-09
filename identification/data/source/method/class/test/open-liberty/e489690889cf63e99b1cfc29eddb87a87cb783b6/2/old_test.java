@Test
    public void recreateTokenFromBytes_InvalidTokenException() throws Exception {
        mock.checking(new Expectations() {
            {
                one(testTokenService).recreateTokenFromBytes(tokenBytes);
                will(throwException(new InvalidTokenException("Expected test exception")));
            }
        });

        try {
            tokenManager.recreateTokenFromBytes(tokenBytes);
            fail("recreateTokenFromBytes should have throw an InvalidTokenException as per the mock setting");
        } catch (InvalidTokenException e) {
            // Success, we expect this exception type
            String msg = "CWWKS4001I: The security token cannot be validated.";
            assertTrue("Unable to find token expiration message",
                       outputMgr.checkForMessages(msg));
            msg = "1. The security token was generated on another server using different keys.";
            assertTrue("Unable to find token expiration message part 1",
                                    outputMgr.checkForMessages(msg));
            msg = "2. The token configuration or the security keys of the token service which created the token has been changed.";
            assertTrue("Unable to find token expiration message part 2",
                                    outputMgr.checkForMessages(msg));
            msg = "3. The token service which created the token is no longer available.";
            assertTrue("Unable to find token expiration message part 3",
                        outputMgr.checkForMessages(msg));
        }

    }