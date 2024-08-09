    @Test(timeout = 30000)
    public void sealStreamTest() throws Exception {
        assertNotEquals(0, consumer.getCurrentSegments(SCOPE, stream1).get().size());
        WriterMock requestEventWriter = new WriterMock(streamMetadataTasks, executor);
        streamMetadataTasks.setRequestEventWriter(requestEventWriter);

        //seal a stream.
        CompletableFuture<UpdateStreamStatus.Status> sealOperationResult = streamMetadataTasks.sealStream(SCOPE, stream1, null);
        assertTrue(Futures.await(processEvent(requestEventWriter)));

        assertEquals(UpdateStreamStatus.Status.SUCCESS, sealOperationResult.get());

        //a sealed stream should have zero active/current segments
        assertEquals(0, consumer.getCurrentSegments(SCOPE, stream1).get().size());
        assertTrue(streamStorePartialMock.isSealed(SCOPE, stream1, null, executor).get());

        //reseal a sealed stream.
        assertEquals(UpdateStreamStatus.Status.SUCCESS, streamMetadataTasks.sealStream(SCOPE, stream1, null).get());
        assertTrue(Futures.await(processEvent(requestEventWriter)));

        //scale operation on the sealed stream.
        AbstractMap.SimpleEntry<Double, Double> segment3 = new AbstractMap.SimpleEntry<>(0.0, 0.2);
        AbstractMap.SimpleEntry<Double, Double> segment4 = new AbstractMap.SimpleEntry<>(0.2, 0.4);
        AbstractMap.SimpleEntry<Double, Double> segment5 = new AbstractMap.SimpleEntry<>(0.4, 0.5);

        ScaleResponse scaleOpResult = streamMetadataTasks.manualScale(SCOPE, stream1, Collections.singletonList(0L),
                Arrays.asList(segment3, segment4, segment5), 30, null).get();

        // scaling operation fails once a stream is sealed.
        assertEquals(ScaleStreamStatus.FAILURE, scaleOpResult.getStatus());

        AssertExtensions.assertFutureThrows("Scale should not be allowed as stream is already sealed",
                streamStorePartialMock.submitScale(SCOPE, stream1, Collections.singletonList(0L), Arrays.asList(segment3, segment4, segment5), 30, null, null, executor),
                e -> Exceptions.unwrap(e) instanceof StoreException.IllegalStateException);
    }