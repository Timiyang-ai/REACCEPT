  @Test
  public void isIntegratedSecurity_returnsTrue() throws Exception {
    boolean result = this.securityService.isIntegratedSecurity();
    assertThat(result).isTrue();
  }