    @Test
    public void createTraceTest1() {
        List<ConsumerRecord> consumerRecordList = new ArrayList<ConsumerRecord>();
        consumerRecordList.add(new ConsumerRecord("Test", 1, 1, "hello", "hello too"));

        doReturn(trace).when(traceContext).newTraceObject();
        doReturn(true).when(trace).canSampled();
        doReturn(recorder).when(trace).getSpanRecorder();
        doReturn(consumerRecordList.iterator()).when(consumerRecords).iterator();

        ConsumerMultiRecordEntryPointInterceptor interceptor = new ConsumerMultiRecordEntryPointInterceptor(traceContext, descriptor, 0);
        interceptor.createTrace(new Object(), new Object[]{consumerRecords});

        verify(recorder).recordAcceptorHost("Unknown");
        verify(recorder).recordAttribute(KafkaConstants.KAFKA_TOPIC_ANNOTATION_KEY, "Test");
        verify(recorder).recordAttribute(KafkaConstants.KAFKA_BATCH_ANNOTATION_KEY, 1);
        verify(recorder).recordRpcName("kafka://topic=Test?batch=1");
    }