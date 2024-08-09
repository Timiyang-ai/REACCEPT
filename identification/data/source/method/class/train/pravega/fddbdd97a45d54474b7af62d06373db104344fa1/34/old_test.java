    private void scale(String scope, String stream, Map<Long, Long> sealedSegmentsWithSize,
                       List<Map.Entry<Double, Double>> newSegments) {
        long scaleTs = System.currentTimeMillis();

        ArrayList<Long> sealedSegments = Lists.newArrayList(sealedSegmentsWithSize.keySet());
        VersionedMetadata<EpochTransitionRecord> response = streamStorePartialMock.submitScale(scope, stream, sealedSegments,
                newSegments, scaleTs, null, null, executor).join();
        response.getObject().getNewSegmentsWithRange();
        VersionedMetadata<State> state = streamStorePartialMock.getVersionedState(scope, stream, null, executor).join();
        state = streamStorePartialMock.updateVersionedState(scope, stream, State.SCALING, state, null, executor).join();
        streamStorePartialMock.startScale(scope, stream, false, response, state, null, executor).join();
        streamStorePartialMock.scaleCreateNewEpochs(scope, stream, response, null, executor).join();
        streamStorePartialMock.scaleSegmentsSealed(scope, stream, sealedSegmentsWithSize, response, null, executor).join();
        streamStorePartialMock.completeScale(scope, stream, response, null, executor).join();
        streamStorePartialMock.setState(scope, stream, State.ACTIVE, null, executor).join();
    }