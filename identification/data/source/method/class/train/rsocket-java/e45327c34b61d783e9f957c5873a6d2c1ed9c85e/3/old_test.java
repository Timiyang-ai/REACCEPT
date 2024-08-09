  @DisplayName("dispose removes name from registry")
  @Test
  void dispose() {
    LocalServerTransport.dispose("test-name");
  }