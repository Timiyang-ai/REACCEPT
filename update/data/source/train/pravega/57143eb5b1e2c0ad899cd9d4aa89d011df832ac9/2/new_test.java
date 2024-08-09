@Test
    public void testListStreams() throws ExecutionException, InterruptedException {
        final String resourceURI = "v1/scopes/scope1/streams";

        final StreamConfiguration streamConfiguration1 = StreamConfiguration.builder()
                .scope(scope1)
                .streamName(stream1)
                .scalingPolicy(ScalingPolicy.byEventRate(100, 2, 2))
                .retentionPolicy(RetentionPolicy.byTime(Duration.ofMillis(123L)))
                .build();

        final StreamConfiguration streamConfiguration2 = StreamConfiguration.builder()
                .scope(scope1)
                .streamName(stream2)
                .scalingPolicy(ScalingPolicy.byEventRate(100, 2, 2))
                .retentionPolicy(RetentionPolicy.byTime(Duration.ofMillis(123L)))
                .build();

        // Test to list streams.
        List<StreamConfiguration> streamsList = Arrays.asList(streamConfiguration1, streamConfiguration2);

        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(CompletableFuture.completedFuture(streamsList));
        response = target(resourceURI).request().async().get();
        final StreamsList streamsList1 = response.get().readEntity(StreamsList.class);
        assertEquals("List Streams response code", 200, response.get().getStatus());
        assertEquals("List count", streamsList1.getStreams().size(), 2);
        assertEquals("List element", streamsList1.getStreams().get(0).getStreamName(), "stream1");
        assertEquals("List element", streamsList1.getStreams().get(1).getStreamName(), "stream2");

        // Test for list streams for invalid scope.
        final CompletableFuture<List<StreamConfiguration>> completableFuture1 = new CompletableFuture<>();
        completableFuture1.completeExceptionally(new DataNotFoundException(""));
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(completableFuture1);
        response = target(resourceURI).request().async().get();
        assertEquals("List Streams response code", 404, response.get().getStatus());

        // Test for list streams failure.
        final CompletableFuture<List<StreamConfiguration>> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new Exception());
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(completableFuture);
        response = target(resourceURI).request().async().get();
        assertEquals("List Streams response code", 500, response.get().getStatus());

        // Test for filtering streams.
        final StreamConfiguration streamConfiguration3 = StreamConfiguration.builder()
                .scope(scope1)
                .streamName(NameUtils.getInternalNameForStream("stream3"))
                .scalingPolicy(ScalingPolicy.fixed(1))
                .retentionPolicy(RetentionPolicy.INFINITE)
                .build();
        List<StreamConfiguration> allStreamsList = Arrays.asList(streamConfiguration1, streamConfiguration2,
                streamConfiguration3);
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(
                CompletableFuture.completedFuture(allStreamsList));
        response = target(resourceURI).request().async().get();
        StreamsList streamsListResp = response.get().readEntity(StreamsList.class);
        assertEquals("List Streams response code", 200, response.get().getStatus());
        assertEquals("List count", 2, streamsListResp.getStreams().size());
        assertEquals("List element", "stream1", streamsListResp.getStreams().get(0).getStreamName());
        assertEquals("List element", "stream2", streamsListResp.getStreams().get(1).getStreamName());
        response = target(resourceURI).queryParam("showInternalStreams", "true").request().async().get();
        streamsListResp = response.get().readEntity(StreamsList.class);
        assertEquals("List Streams response code", 200, response.get().getStatus());
        assertEquals("List count", 1, streamsListResp.getStreams().size());
        assertEquals("List element", NameUtils.getInternalNameForStream("stream3"),
                streamsListResp.getStreams().get(0).getStreamName());

        // Test to list large number of streams.
        streamsList = Collections.nCopies(1000, streamConfiguration1);
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(CompletableFuture.completedFuture(streamsList));
        response = target(resourceURI).request().async().get();
        final StreamsList streamsList2 = response.get().readEntity(StreamsList.class);
        assertEquals("List Streams response code", 200, response.get().getStatus());
        assertEquals("List count", 200, streamsList2.getStreams().size());
    }