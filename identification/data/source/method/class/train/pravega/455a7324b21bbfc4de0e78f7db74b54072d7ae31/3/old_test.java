    @Test(timeout = 30000)
    public void truncateStreamTest() throws Exception {
        final ScalingPolicy policy = ScalingPolicy.fixed(2);

        final StreamConfiguration configuration = StreamConfiguration.builder().scalingPolicy(policy).build();

        streamStorePartialMock.createStream(SCOPE, "test", configuration, System.currentTimeMillis(), null, executor).get();
        streamStorePartialMock.setState(SCOPE, "test", State.ACTIVE, null, executor).get();

        assertNotEquals(0, consumer.getCurrentSegments(SCOPE, "test").get().size());
        WriterMock requestEventWriter = new WriterMock(streamMetadataTasks, executor);
        streamMetadataTasks.setRequestEventWriter(requestEventWriter);

        List<Map.Entry<Double, Double>> newRanges = new ArrayList<>();
        newRanges.add(new AbstractMap.SimpleEntry<>(0.5, 0.75));
        newRanges.add(new AbstractMap.SimpleEntry<>(0.75, 1.0));
        ScaleResponse scaleOpResult = streamMetadataTasks.manualScale(SCOPE, "test", Collections.singletonList(1L),
                newRanges, 30, null).get();
        assertTrue(scaleOpResult.getStatus().equals(ScaleStreamStatus.STARTED));

        ScaleOperationTask scaleTask = new ScaleOperationTask(streamMetadataTasks, streamStorePartialMock, executor);
        assertTrue(Futures.await(scaleTask.execute((ScaleOpEvent) requestEventWriter.eventQueue.take())));

        // start truncation
        StreamTruncationRecord truncProp = streamStorePartialMock.getTruncationRecord(SCOPE, "test",
                null, executor).join().getObject();
        assertFalse(truncProp.isUpdating());
        // 1. happy day test
        // update.. should succeed
        Map<Long, Long> streamCut = new HashMap<>();
        streamCut.put(0L, 1L);
        streamCut.put(1L, 11L);
        CompletableFuture<UpdateStreamStatus.Status> truncateFuture = streamMetadataTasks.truncateStream(SCOPE, "test",
                streamCut, null);
        assertTrue(Futures.await(processEvent(requestEventWriter)));
        assertEquals(UpdateStreamStatus.Status.SUCCESS, truncateFuture.join());

        truncProp = streamStorePartialMock.getTruncationRecord(SCOPE, "test", null, executor).join().getObject();
        assertTrue(truncProp.getStreamCut().equals(streamCut));
        assertTrue(truncProp.getStreamCut().equals(streamCut));

        // 2. change state to scaling
        streamStorePartialMock.setState(SCOPE, "test", State.SCALING, null, executor).get();
        // call update should fail without posting the event
        long two = StreamSegmentNameUtils.computeSegmentId(2, 1);
        long three = StreamSegmentNameUtils.computeSegmentId(3, 1);
        Map<Long, Long> streamCut2 = new HashMap<>();
        streamCut2.put(0L, 1L);
        streamCut2.put(two, 1L);
        streamCut2.put(three, 1L);

        streamMetadataTasks.truncateStream(SCOPE, "test", streamCut2, null);

        AtomicBoolean loop = new AtomicBoolean(false);
        Futures.loop(() -> !loop.get(),
                () -> Futures.delayedFuture(() -> streamStorePartialMock.getTruncationRecord(SCOPE, "test", null, executor), 1000, executor)
                        .thenApply(x -> x.getObject().isUpdating())
                        .thenAccept(loop::set), executor).join();

        // event posted, first step performed. now pick the event for processing
        TruncateStreamTask truncateStreamTask = new TruncateStreamTask(streamMetadataTasks, streamStorePartialMock, executor);
        TruncateStreamEvent taken = (TruncateStreamEvent) requestEventWriter.eventQueue.take();
        AssertExtensions.assertFutureThrows("", truncateStreamTask.execute(taken),
                e -> Exceptions.unwrap(e) instanceof StoreException.OperationNotAllowedException);

        streamStorePartialMock.setState(SCOPE, "test", State.ACTIVE, null, executor).get();

        // now with state = active, process the same event. it should succeed now.
        assertTrue(Futures.await(truncateStreamTask.execute(taken)));

        // 3. multiple back to back updates.

        Map<Long, Long> streamCut3 = new HashMap<>();
        streamCut3.put(0L, 12L);
        streamCut3.put(two, 12L);
        streamCut3.put(three, 12L);
        CompletableFuture<UpdateStreamStatus.Status> truncateOp1 = streamMetadataTasks.truncateStream(SCOPE, "test",
                streamCut3, null);

        // ensure that previous updatestream has posted the event and set status to updating,
        // only then call second updateStream
        AtomicBoolean loop2 = new AtomicBoolean(false);
        Futures.loop(() -> !loop2.get(),
                () -> streamStorePartialMock.getTruncationRecord(SCOPE, "test", null, executor)
                        .thenApply(x -> x.getObject().isUpdating())
                        .thenAccept(loop2::set), executor).join();

        truncProp = streamStorePartialMock.getTruncationRecord(SCOPE, "test", null, executor).join().getObject();
        assertTrue(truncProp.getStreamCut().equals(streamCut3) && truncProp.isUpdating());

        // post the second update request. This should fail here itself as previous one has started.
        Map<Long, Long> streamCut4 = new HashMap<>();
        streamCut4.put(0L, 14L);
        streamCut4.put(two, 14L);
        streamCut4.put(three, 14L);
        CompletableFuture<UpdateStreamStatus.Status> truncateOpFuture2 = streamMetadataTasks.truncateStream(SCOPE, "test",
                streamCut4, null);
        assertEquals(UpdateStreamStatus.Status.FAILURE, truncateOpFuture2.join());

        // process event
        assertTrue(Futures.await(processEvent(requestEventWriter)));
        // verify that first request for update also completes with success.
        assertEquals(UpdateStreamStatus.Status.SUCCESS, truncateOp1.join());

        truncProp = streamStorePartialMock.getTruncationRecord(SCOPE, "test", null, executor).join().getObject();
        assertTrue(truncProp.getStreamCut().equals(streamCut3) && !truncProp.isUpdating());

        streamStorePartialMock.setState(SCOPE, "test", State.TRUNCATING, null, executor).join();

        TruncateStreamEvent event = new TruncateStreamEvent(SCOPE, "test", System.nanoTime());
        assertTrue(Futures.await(truncateStreamTask.execute(event)));
        AssertExtensions.assertFutureThrows("", truncateStreamTask.execute(event), e -> Exceptions.unwrap(e) instanceof TaskExceptions.StartException);

        assertEquals(State.ACTIVE, streamStorePartialMock.getState(SCOPE, "test", true, null, executor).join());
    }