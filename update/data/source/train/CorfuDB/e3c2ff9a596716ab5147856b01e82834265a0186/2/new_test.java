@Test
    void retryReadRecordsTimeout() {
        List<Long> addresses = LongStream.range(0L, 10L).boxed().collect(Collectors.toList());

        List<LogData> secondReadList = createStubList(addresses);
        Map<Long, ILogData> secondReadMap = createStubMap(addresses);

        ReadOptions readOptions = ProtocolBatchProcessor.getReadOptions();
        StreamLog streamLog = mock(StreamLog.class);

        doNothing().when(streamLog).append(secondReadList);

        AddressSpaceView addressSpaceView = mock(AddressSpaceView.class);

        doAnswer(answer -> {
            throw new TimeoutException();
        }).when(addressSpaceView).simpleProtocolRead(addresses, readOptions);

        List<LogData> secondReturnedRecords = getRecordsFromStubMap(secondReadMap);

        ProtocolBatchProcessor batchProcessor = new ProtocolBatchProcessor(streamLog, addressSpaceView);
        ProtocolBatchProcessor spy = spy(batchProcessor);

        CompletableFuture<Result<List<LogData>, BatchProcessorFailure>> res =
                spy.retryReadRecords(addresses, 0);

        Result<List<LogData>, BatchProcessorFailure> join = res.join();
        assertThat(join.isError()).isTrue();
        assertThat(join.getError().getThrowable()).isInstanceOf(RetryExhaustedException.class);

    }