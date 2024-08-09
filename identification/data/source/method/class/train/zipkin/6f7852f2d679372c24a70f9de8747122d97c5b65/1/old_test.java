  @Test
  public void acceptSpans_jsonV2() {
    byte[] bytes = SpanBytesEncoder.JSON_V2.encodeList(TRACE);
    collector.acceptSpans(bytes, callback);

    verify(collector).acceptSpans(bytes, SpanBytesDecoder.JSON_V2, callback);

    verify(callback).onSuccess(null);
    assertThat(testLogger.getLoggingEvents()).isEmpty();
    verify(metrics).incrementSpans(4);
    assertThat(storage.getTraces()).containsOnly(TRACE);
  }