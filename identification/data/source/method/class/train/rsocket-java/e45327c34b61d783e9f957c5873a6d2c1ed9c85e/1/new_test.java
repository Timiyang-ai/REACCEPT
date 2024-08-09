  @DisplayName("creates client")
  @Test
  void create() {
    assertThat(LocalClientTransport.create("test-name")).isNotNull();
  }