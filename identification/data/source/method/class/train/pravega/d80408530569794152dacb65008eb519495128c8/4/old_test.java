    @Test
    public void createOutputStreamForSegment() {
        EventWriterConfig writerConfig = EventWriterConfig.builder().enableConnectionPooling(false).build();
        SegmentOutputStreamImpl segWriter = (SegmentOutputStreamImpl) factory.createOutputStreamForSegment(segment, writerConfig,
                DelegationTokenProviderFactory.createWithEmptyToken());
        assertEquals(segment.getScopedName(), segWriter.getSegmentName());
        assertEquals(writerConfig.isEnableConnectionPooling(), segWriter.isUseConnectionPooling());
    }