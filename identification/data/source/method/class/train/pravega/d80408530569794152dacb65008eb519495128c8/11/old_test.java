    @Test
    public void createOutputStreamForTransaction() {
        EventWriterConfig writerConfig = EventWriterConfig.builder().build();

        SegmentOutputStreamImpl segWriter = (SegmentOutputStreamImpl) factory.createOutputStreamForTransaction(segment, txId, writerConfig,
                DelegationTokenProviderFactory.createWithEmptyToken());
        assertTrue(isTransactionSegment(segWriter.getSegmentName()));
        assertEquals(writerConfig.isEnableConnectionPooling(), segWriter.isUseConnectionPooling());
    }