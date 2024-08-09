  @Test
  public void accept_storageError() {
    StorageComponent storage = mock(StorageComponent.class);
    RuntimeException error = new RuntimeException("storage disabled");
    when(storage.spanConsumer()).thenThrow(error);
    collector = new Collector.Builder(LoggerFactory.getLogger(""))
      .metrics(metrics)
      .storage(storage)
      .build();

    collector.accept(TRACE, callback);

    verify(callback).onSuccess(null); // error is async
    assertDebugLogIs("Cannot store spans [1, 2, 2, ...] due to RuntimeException(storage disabled)");
    verify(metrics).incrementSpans(4);
    verify(metrics).incrementSpansDropped(4);
  }