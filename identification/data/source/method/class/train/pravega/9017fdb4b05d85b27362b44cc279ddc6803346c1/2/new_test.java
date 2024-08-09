    @Test(timeout = 30000)
    public void manualScaleTest() throws Exception {
        final ScalingPolicy policy = ScalingPolicy.fixed(1);

        final StreamConfiguration configuration = StreamConfiguration.builder().scalingPolicy(policy).build();

        streamStorePartialMock.createStream(SCOPE, "test", configuration, System.currentTimeMillis(), null, executor).get();
        streamStorePartialMock.setState(SCOPE, "test", State.ACTIVE, null, executor).get();

        WriterMock requestEventWriter = new WriterMock(streamMetadataTasks, executor);
        streamMetadataTasks.setRequestEventWriter(requestEventWriter);
        List<Map.Entry<Double, Double>> newRanges = new ArrayList<>();
        newRanges.add(new AbstractMap.SimpleEntry<>(0.0, 0.5));
        newRanges.add(new AbstractMap.SimpleEntry<>(0.5, 1.0));
        ScaleResponse scaleOpResult = streamMetadataTasks.manualScale(SCOPE, "test", Collections.singletonList(0L),
                newRanges, 30, null).get();

        assertEquals(ScaleStreamStatus.STARTED, scaleOpResult.getStatus());
        OperationContext context = streamStorePartialMock.createContext(SCOPE, "test");
        assertEquals(streamStorePartialMock.getState(SCOPE, "test", false, context, executor).get(), State.ACTIVE);

        // Now when runScale runs even after that we should get the state as active.
        VersionedMetadata<EpochTransitionRecord> response = streamStorePartialMock.submitScale(SCOPE, "test", Collections.singletonList(0L),
                new LinkedList<>(newRanges), 30, null, null, executor).get();
        assertEquals(response.getObject().getActiveEpoch(), 0);
        VersionedMetadata<State> versionedState = streamStorePartialMock.getVersionedState(SCOPE, "test", context, executor).get();
        assertEquals(versionedState.getObject(), State.ACTIVE);

        // if we call start scale without scale being set to SCALING, this should throw illegal argument exception
        AssertExtensions.assertThrows("", () -> streamStorePartialMock.startScale(SCOPE, "test", true,
                response, versionedState, context, executor).get(), ex -> Exceptions.unwrap(ex) instanceof IllegalArgumentException);

        ScaleOperationTask task = new ScaleOperationTask(streamMetadataTasks, streamStorePartialMock, executor);
        task.runScale((ScaleOpEvent) requestEventWriter.getEventQueue().take(), true, context, "").get();
        Map<Long, Map.Entry<Double, Double>> segments = response.getObject().getNewSegmentsWithRange();
        assertTrue(segments.entrySet().stream()
                .anyMatch(x -> x.getKey() == computeSegmentId(1, 1)
                             && AssertExtensions.nearlyEquals(x.getValue().getKey(), 0.0, 0)
                             && AssertExtensions.nearlyEquals(x.getValue().getValue(), 0.5, 0)));
        assertTrue(segments.entrySet().stream()
                .anyMatch(x -> x.getKey() == computeSegmentId(2, 1)
                             && AssertExtensions.nearlyEquals(x.getValue().getKey(), 0.5, 0)
                             && AssertExtensions.nearlyEquals(x.getValue().getValue(), 1.0, 0)));
    }