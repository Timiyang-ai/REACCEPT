@Test void check() {
    server.enqueue(HEALTH_RESPONSE);

    assertThat(storage.check()).isEqualTo(CheckResult.OK);
  }