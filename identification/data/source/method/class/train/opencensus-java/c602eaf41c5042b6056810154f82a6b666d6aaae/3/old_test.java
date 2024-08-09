  @Test
  public void handleMessageReceived() {
    Type type = Type.RECEIVED;
    long uncompressed = 456L;
    HttpRequestContext context = new HttpRequestContext(fakeSpan, tagContext);
    handler.handleMessageReceived(context, uncompressed);
    verify(fakeSpan).addMessageEvent(captor.capture());

    MessageEvent messageEvent = captor.getValue();
    assertThat(messageEvent.getType()).isEqualTo(type);
    assertThat(messageEvent.getMessageId()).isEqualTo(1L);
    assertThat(messageEvent.getUncompressedMessageSize()).isEqualTo(uncompressed);
    assertThat(messageEvent.getCompressedMessageSize()).isEqualTo(0);
  }