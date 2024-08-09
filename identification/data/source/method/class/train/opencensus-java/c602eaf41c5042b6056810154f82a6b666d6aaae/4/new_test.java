  @Test
  public void handleMessageSent() {
    Type type = Type.SENT;
    long uncompressed = 456L;
    HttpRequestContext context = new HttpRequestContext(fakeSpan, tagContext);
    handler.handleMessageSent(context, uncompressed);
    verify(fakeSpan).addMessageEvent(captor.capture());

    MessageEvent messageEvent = captor.getValue();
    assertThat(messageEvent.getType()).isEqualTo(type);
    assertThat(messageEvent.getMessageId()).isEqualTo(1L);
    assertThat(messageEvent.getUncompressedMessageSize()).isEqualTo(uncompressed);
    assertThat(messageEvent.getCompressedMessageSize()).isEqualTo(0);
  }