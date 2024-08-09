@Test
    public void recreateTokenFromBytes() throws Exception {
        mock.checking(new Expectations() {
            {
                allowing(testTokenService).recreateTokenFromBytes(tokenBytes);
                allowing(testTokenService).recreateTokenFromBytes(tokenBytes, removeAttrs);
            }
        });

        assertNotNull("Mock should return a non-null token",
                      tokenManager.recreateTokenFromBytes(tokenBytes));
        assertNotNull("Mock should return a non-null token",
                      tokenManager.recreateTokenFromBytes(tokenBytes, removeAttrs));

    }