    @Test(timeout = 30000)
    public void updateStreamTest() throws Exception {
        assertNotEquals(0, consumer.getCurrentSegments(SCOPE, stream1).get().size());
        WriterMock requestEventWriter = new WriterMock(streamMetadataTasks, executor);
        streamMetadataTasks.setRequestEventWriter(requestEventWriter);

        StreamConfiguration streamConfiguration = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.fixed(5)).build();

        StreamConfigurationRecord configProp = streamStorePartialMock.getConfigurationRecord(SCOPE, stream1, null, executor).join().getObject();
        assertFalse(configProp.isUpdating());
        // 1. happy day test
        // update.. should succeed
        CompletableFuture<UpdateStreamStatus.Status> updateOperationFuture = streamMetadataTasks.updateStream(SCOPE, stream1, streamConfiguration, null);
        assertTrue(Futures.await(processEvent(requestEventWriter)));
        assertEquals(UpdateStreamStatus.Status.SUCCESS, updateOperationFuture.join());

        configProp = streamStorePartialMock.getConfigurationRecord(SCOPE, stream1, null, executor).join().getObject();
        assertTrue(configProp.getStreamConfiguration().equals(streamConfiguration));

        streamConfiguration = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.fixed(6)).build();

        // 2. change state to scaling
        streamStorePartialMock.setState(SCOPE, stream1, State.SCALING, null, executor).get();
        // call update should fail without posting the event
        streamMetadataTasks.updateStream(SCOPE, stream1, streamConfiguration, null);

        AtomicBoolean loop = new AtomicBoolean(false);
        Futures.loop(() -> !loop.get(),
                () -> streamStorePartialMock.getConfigurationRecord(SCOPE, stream1, null, executor)
                        .thenApply(x -> x.getObject().isUpdating())
                        .thenAccept(loop::set), executor).join();

        // event posted, first step performed. now pick the event for processing
        UpdateStreamTask updateStreamTask = new UpdateStreamTask(streamMetadataTasks, streamStorePartialMock, bucketStore, executor);
        UpdateStreamEvent taken = (UpdateStreamEvent) requestEventWriter.eventQueue.take();
        AssertExtensions.assertFutureThrows("", updateStreamTask.execute(taken),
                e -> Exceptions.unwrap(e) instanceof StoreException.OperationNotAllowedException);

        streamStorePartialMock.setState(SCOPE, stream1, State.ACTIVE, null, executor).get();

        // now with state = active, process the same event. it should succeed now.
        assertTrue(Futures.await(updateStreamTask.execute(taken)));

        // 3. multiple back to back updates.
        StreamConfiguration streamConfiguration1 = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.byEventRate(1, 1, 2)).build();

        CompletableFuture<UpdateStreamStatus.Status> updateOperationFuture1 = streamMetadataTasks.updateStream(SCOPE, stream1,
                streamConfiguration1, null);

        // ensure that previous updatestream has posted the event and set status to updating,
        // only then call second updateStream
        AtomicBoolean loop2 = new AtomicBoolean(false);
        Futures.loop(() -> !loop2.get(),
                () -> streamStorePartialMock.getConfigurationRecord(SCOPE, stream1, null, executor)
                        .thenApply(x -> x.getObject().isUpdating())
                        .thenAccept(loop2::set), executor).join();

        configProp = streamStorePartialMock.getConfigurationRecord(SCOPE, stream1, null, executor).join().getObject();
        assertTrue(configProp.getStreamConfiguration().equals(streamConfiguration1) && configProp.isUpdating());

        StreamConfiguration streamConfiguration2 = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.fixed(7)).build();

        // post the second update request. This should fail here itself as previous one has started.
        CompletableFuture<UpdateStreamStatus.Status> updateOperationFuture2 = streamMetadataTasks.updateStream(SCOPE, stream1,
                streamConfiguration2, null);
        assertEquals(UpdateStreamStatus.Status.FAILURE, updateOperationFuture2.join());

        // process event
        assertTrue(Futures.await(processEvent(requestEventWriter)));
        // verify that first request for update also completes with success.
        assertEquals(UpdateStreamStatus.Status.SUCCESS, updateOperationFuture1.join());

        configProp = streamStorePartialMock.getConfigurationRecord(SCOPE, stream1, null, executor).join().getObject();
        assertTrue(configProp.getStreamConfiguration().equals(streamConfiguration1) && !configProp.isUpdating());

        streamStorePartialMock.setState(SCOPE, stream1, State.UPDATING, null, executor).join();
        UpdateStreamEvent event = new UpdateStreamEvent(SCOPE, stream1, System.nanoTime());
        assertTrue(Futures.await(updateStreamTask.execute(event)));
        AssertExtensions.assertFutureThrows("", updateStreamTask.execute(event), e -> Exceptions.unwrap(e) instanceof TaskExceptions.StartException);
        assertEquals(State.ACTIVE, streamStorePartialMock.getState(SCOPE, stream1, true, null, executor).join());
    }