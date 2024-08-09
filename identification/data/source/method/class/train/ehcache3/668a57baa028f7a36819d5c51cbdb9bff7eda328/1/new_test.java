  @Test
  public void test_append_doesNotConsumeBuffer() throws Exception {
    ServerStore store = newStore();
    ByteBuffer payload = createPayload(1L);

    store.append(1L, payload);
    assertThat(payload.remaining(), Is.is(8));
  }