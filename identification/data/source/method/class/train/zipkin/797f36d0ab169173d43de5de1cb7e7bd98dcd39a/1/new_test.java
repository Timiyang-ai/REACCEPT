@Test void check_fail_onNoContent() {
    storage.indexTemplates = mock(IndexTemplates.class); // assume index templates called before

    server.enqueue(SUCCESS_RESPONSE); // empty instead of success response

    CheckResult result = storage.check();
    assertThat(result.ok()).isFalse();
    assertThat(result.error().getMessage())
      .isEqualTo("No content reading cluster health");
  }