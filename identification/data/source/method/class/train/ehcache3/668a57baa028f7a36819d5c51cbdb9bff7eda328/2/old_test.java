  @Test
  public void test_getAndAppend_doesNotConsumeBuffer() throws Exception {
    ServerStore store = newStore();
    ByteBuffer payload = createPayload(1L);

    store.getAndAppend(1L, payload);
    assertThat(payload.remaining(), Is.is(8));
  }