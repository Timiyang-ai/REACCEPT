  @Test
  public void needPostProcess_returnsFalse() throws Exception {
    boolean needPostProcess = this.securityService.needPostProcess();
    assertThat(needPostProcess).isFalse();
  }