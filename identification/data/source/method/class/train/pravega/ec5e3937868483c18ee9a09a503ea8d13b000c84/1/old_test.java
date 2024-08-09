    @Test
    public void scaleTest() throws Exception {
        final String scope = "ScopeScale";
        final String stream = "StreamScale";
        final ScalingPolicy policy = ScalingPolicy.fixed(2);
        final StreamConfiguration configuration = StreamConfiguration.builder().scalingPolicy(policy).build();

        long start = System.currentTimeMillis();
        store.createScope(scope).get();

        store.createStream(scope, stream, configuration, start, null, executor).get();
        store.setState(scope, stream, State.ACTIVE, null, executor).get();

        // region idempotent

        long scaleTs = System.currentTimeMillis();
        SimpleEntry<Double, Double> segment1 = new SimpleEntry<>(0.5, 0.75);
        SimpleEntry<Double, Double> segment2 = new SimpleEntry<>(0.75, 1.0);
        List<Long> scale1SealedSegments = Collections.singletonList(computeSegmentId(1, 0));

        // 1. submit scale
        VersionedMetadata<EpochTransitionRecord> empty = store.getEpochTransition(scope, stream, null, executor).join();
        
        VersionedMetadata<EpochTransitionRecord> response = store.submitScale(scope, stream, scale1SealedSegments,
                Arrays.asList(segment1, segment2), scaleTs, null, null, executor).join();
        Map<Long, Map.Entry<Double, Double>> scale1SegmentsCreated = response.getObject().getNewSegmentsWithRange();
        final int scale1ActiveEpoch = response.getObject().getActiveEpoch();
        assertEquals(0, scale1ActiveEpoch);
        
        // rerun start scale with old epoch transition. should throw write conflict
        AssertExtensions.assertSuppliedFutureThrows("", () -> store.submitScale(scope, stream, scale1SealedSegments,
                Arrays.asList(segment1, segment2), scaleTs, empty, null, executor),
                e -> Exceptions.unwrap(e) instanceof StoreException.WriteConflictException);

        // rerun start scale with null epoch transition, should be idempotent
        response = store.submitScale(scope, stream, scale1SealedSegments,
                Arrays.asList(segment1, segment2), scaleTs, null, null, executor).join();
        assertEquals(response.getObject().getNewSegmentsWithRange(), scale1SegmentsCreated);

        VersionedMetadata<State> state = store.getVersionedState(scope, stream, null, executor).join();
        state = store.updateVersionedState(scope, stream, State.SCALING, state, null, executor).get();
        response = store.startScale(scope, stream, false, response, state, null, executor).join();
        // 2. scale new segments created
        store.scaleCreateNewEpochs(scope, stream, response, null, executor).join();

        // rerun start scale and new segments created
        response = store.submitScale(scope, stream, scale1SealedSegments,
                Arrays.asList(segment1, segment2), scaleTs, null, null, executor).join();
        assertEquals(response.getObject().getNewSegmentsWithRange(), scale1SegmentsCreated);
        
        response = store.startScale(scope, stream, false, response, state, null, executor).join();
        store.scaleCreateNewEpochs(scope, stream, response, null, executor).join();

        // 3. scale segments sealed -- this will complete scale
        store.scaleSegmentsSealed(scope, stream, scale1SealedSegments.stream().collect(Collectors.toMap(x -> x, x -> 0L)), response,
                null, executor).join();
        store.completeScale(scope, stream, response, null, executor).join();
        store.setState(scope, stream, State.ACTIVE, null, executor).get();

        // rerun -- idempotent
        store.scaleCreateNewEpochs(scope, stream, response, null, executor).join();
        EpochRecord activeEpoch = store.getActiveEpoch(scope, stream, null, true, executor).join();
        assertEquals(1, activeEpoch.getEpoch());
        
        store.scaleSegmentsSealed(scope, stream, scale1SealedSegments.stream().collect(Collectors.toMap(x -> x, x -> 0L)), 
                response, null, executor).join();
        store.getActiveEpoch(scope, stream, null, true, executor).join();
        assertEquals(1, activeEpoch.getEpoch());

        // rerun submit scale -- should fail with precondition failure
        VersionedMetadata<EpochTransitionRecord> etr = store.getEpochTransition(scope, stream, null, executor).join();
        assertEquals(EpochTransitionRecord.EMPTY, empty.getObject());

        AssertExtensions.assertThrows("Submit scale with old data with old etr", () ->
                        store.submitScale(scope, stream, scale1SealedSegments,
                                Arrays.asList(segment1, segment2), scaleTs, empty, null, executor).join(),
                e -> Exceptions.unwrap(e) instanceof StoreException.WriteConflictException);

        AssertExtensions.assertThrows("Submit scale with old data with latest etr", () ->
                        store.submitScale(scope, stream, scale1SealedSegments,
                                Arrays.asList(segment1, segment2), scaleTs, etr, null, executor).join(),
                e -> Exceptions.unwrap(e) instanceof EpochTransitionOperationExceptions.PreConditionFailureException);

        AssertExtensions.assertThrows("Submit scale with null etr", () ->
                store.submitScale(scope, stream, scale1SealedSegments,
                        Arrays.asList(segment1, segment2), scaleTs, null, null, executor).join(),
                e -> Exceptions.unwrap(e) instanceof EpochTransitionOperationExceptions.PreConditionFailureException);
        // endregion

        // 2 different conflicting scale operations
        // region run concurrent conflicting scale
        SimpleEntry<Double, Double> segment3 = new SimpleEntry<>(0.0, 0.5);
        SimpleEntry<Double, Double> segment4 = new SimpleEntry<>(0.5, 0.75);
        SimpleEntry<Double, Double> segment5 = new SimpleEntry<>(0.75, 1.0);
        List<Long> scale2SealedSegments = Arrays.asList(computeSegmentId(0, 0), computeSegmentId(2, 1), computeSegmentId(3, 1));
        long scaleTs2 = System.currentTimeMillis();
        response = store.submitScale(scope, stream, scale2SealedSegments, Arrays.asList(segment3, segment4, segment5), scaleTs2, null, null, executor).get();
        Map<Long, Map.Entry<Double, Double>> scale2SegmentsCreated = response.getObject().getNewSegmentsWithRange();
        final int scale2ActiveEpoch = response.getObject().getActiveEpoch();
        store.setState(scope, stream, State.SCALING, null, executor).get();

        // rerun of scale 1 -- should fail with conflict
        AssertExtensions.assertThrows("Concurrent conflicting scale", () ->
                store.submitScale(scope, stream, scale1SealedSegments,
                        Arrays.asList(segment1, segment2), scaleTs, null, null, executor).join(),
                e -> Exceptions.unwrap(e) instanceof EpochTransitionOperationExceptions.ConflictException);

        store.scaleCreateNewEpochs(scope, stream, response, null, executor).get();

        store.scaleSegmentsSealed(scope, stream, scale1SealedSegments.stream().collect(Collectors.toMap(x -> x, x -> 0L)), response,
                null, executor).get();
        store.completeScale(scope, stream, response, null, executor).join();
        store.setState(scope, stream, State.ACTIVE, null, executor).get();
        // endregion

        // region concurrent submit scale requests
        // run two concurrent runScale operations such that after doing a getEpochTransition, we create a new epoch
        // transition node. We should get ScaleConflict in such a case.
        // mock createEpochTransition
        SimpleEntry<Double, Double> segment6 = new SimpleEntry<>(0.0, 1.0);
        List<Long> scale3SealedSegments = Arrays.asList(computeSegmentId(4, 2), computeSegmentId(5, 2), computeSegmentId(6, 2));
        long scaleTs3 = System.currentTimeMillis();

        @SuppressWarnings("unchecked")
        PersistentStreamBase streamObj = (PersistentStreamBase) ((AbstractStreamMetadataStore) store).getStream(scope, stream, null);
        PersistentStreamBase streamObjSpied = spy(streamObj);

        CompletableFuture<Void> latch = new CompletableFuture<>();
        CompletableFuture<Void> updateEpochTransitionCalled = new CompletableFuture<>();

        doAnswer(x -> CompletableFuture.runAsync(() -> {
            // wait until we create epoch transition outside of this method
            updateEpochTransitionCalled.complete(null);
            latch.join();
        }).thenCompose(v -> streamObj.updateEpochTransitionNode(x.getArgument(0)))).when(streamObjSpied).updateEpochTransitionNode(any());

        doAnswer(x -> streamObj.getEpochTransitionNode()).when(streamObjSpied).getEpochTransitionNode();

        ((AbstractStreamMetadataStore) store).setStream(streamObjSpied);

        // the following should be stuck at createEpochTransition
        CompletableFuture<VersionedMetadata<EpochTransitionRecord>> resp = store.submitScale(scope, stream, scale3SealedSegments, Arrays.asList(segment6), scaleTs3, null, null, executor);
        updateEpochTransitionCalled.join();
        VersionedMetadata<EpochTransitionRecord> epochRecord = streamObj.getEpochTransition().join();
        streamObj.updateEpochTransitionNode(new VersionedMetadata<>(EpochTransitionRecord.EMPTY, epochRecord.getVersion())).join();
        latch.complete(null);

        AssertExtensions.assertFutureThrows("", resp, e -> Exceptions.unwrap(e) instanceof StoreException.WriteConflictException);
        // endregion
    }