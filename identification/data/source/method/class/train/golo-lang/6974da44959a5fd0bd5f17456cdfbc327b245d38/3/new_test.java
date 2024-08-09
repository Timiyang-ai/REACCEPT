  @Test
  public void andThen() throws Throwable {
    assertThat(ping.andThen(ping).invoke("Plop"), is("Plop"));
    assertThat(noParam.andThen(ping).invoke(), is(42));
    assertThat(ping.andThen(collectN).invoke("Plop"), is("Plop"));
    assertThat(ping.andThen(collectAny).invoke("Plop"), is("Plop"));
  }