@Test
    public void recreateTokenFromBytes() throws Exception {
        mock.checking(new Expectations() {
            {
                one(testTokenService).recreateTokenFromBytes(tokenBytes);
            }
        });

        assertNotNull("Mock should return a non-null token",
                          tokenManager.recreateTokenFromBytes(tokenBytes));

    }