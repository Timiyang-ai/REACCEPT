  @Test void check_unauthorized() {
    server.enqueue(RESPONSE_UNAUTHORIZED);

    CheckResult result = storage.check();
    assertThat(result.ok()).isFalse();
    assertThat(result.error().getMessage())
      .isEqualTo("User: anonymous is not authorized to perform: es:ESHttpGet");
  }