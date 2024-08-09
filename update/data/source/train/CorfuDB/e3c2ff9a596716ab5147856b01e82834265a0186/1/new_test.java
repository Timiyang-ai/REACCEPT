@Test
    void retryReadRecordsIncomplete() {
        List<Long> addresses = LongStream.range(0L, 10L).boxed().collect(Collectors.toList());
        List<Long> readAddresses = LongStream.range(0L, 10L).boxed().filter(x -> x % 2 == 0).collect(Collectors.toList());
        List<Long> unreadAddresses = LongStream.range(0L, 10L).boxed().filter(x -> x % 2 != 0).collect(Collectors.toList());

        List<LogData> firstReadList = createStubList(readAddresses);
        Map<Long, ILogData> firstReadMap = createStubMap(readAddresses);
        List<LogData> secondReadList = createStubList(unreadAddresses);
        Map<Long, ILogData> secondReadMap = createStubMap(unreadAddresses);

        ReadOptions readOptions = ProtocolBatchProcessor.getReadOptions();
        StreamLog streamLog = mock(StreamLog.class);
        doNothing().when(streamLog).append(firstReadList);
        doNothing().when(streamLog).append(secondReadList);

        AddressSpaceView addressSpaceView = mock(AddressSpaceView.class);
        doReturn(firstReadMap).when(addressSpaceView).simpleProtocolRead(addresses, readOptions);
        doReturn(secondReadMap).when(addressSpaceView).simpleProtocolRead(unreadAddresses, readOptions);

        List<LogData> secondReturnedRecords = getRecordsFromStubMap(secondReadMap);

        ProtocolBatchProcessor batchProcessor = new ProtocolBatchProcessor(streamLog, addressSpaceView);
        ProtocolBatchProcessor spy = spy(batchProcessor);

        CompletableFuture<Result<List<LogData>, BatchProcessorFailure>> res =
                spy.retryReadRecords(addresses, 0);

        Result<List<LogData>, BatchProcessorFailure> join = res.join();
        assertThat(join.isValue()).isTrue();
        assertThat(join.get()).isEqualTo(secondReturnedRecords);

    }