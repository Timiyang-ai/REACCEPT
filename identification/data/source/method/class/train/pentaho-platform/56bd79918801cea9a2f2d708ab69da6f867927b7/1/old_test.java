@Test
  public void testGetAuthenticationProvider() throws Exception {
    AuthenticationProvider authenticationProvider = systemResource.getAuthenticationProvider();

    // default configuration should be JCR
    AuthenticationProvider expectedResult = new AuthenticationProvider(PentahoSpringBeansConfig.AuthenticationProvider.JCR_BASED_AUTHENTICATION.toString());

    Assert.assertTrue(authenticationProvider.toString().equals(expectedResult.toString()));
  }