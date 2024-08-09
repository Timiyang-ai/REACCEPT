@Test
    public void testListStreams() throws ExecutionException, InterruptedException {
        final String resourceURI = getURI() + "v1/scopes/scope1/streams";

        final StreamConfiguration streamConfiguration1 = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.byEventRate(100, 2, 2))
                .retentionPolicy(RetentionPolicy.byTime(Duration.ofMillis(123L)))
                .build();

        final StreamConfiguration streamConfiguration2 = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.byEventRate(100, 2, 2))
                .retentionPolicy(RetentionPolicy.byTime(Duration.ofMillis(123L)))
                .build();

        // Test to list streams.
        Map<String, StreamConfiguration> streamsList = ImmutableMap.of(stream1, streamConfiguration1, stream2, streamConfiguration2);

        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(CompletableFuture.completedFuture(streamsList));
        Response response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Streams response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());
        final StreamsList streamsList1 = response.readEntity(StreamsList.class);
        assertEquals("List count", streamsList1.getStreams().size(), 2);
        assertEquals("List element", streamsList1.getStreams().get(0).getStreamName(), "stream1");
        assertEquals("List element", streamsList1.getStreams().get(1).getStreamName(), "stream2");
        response.close();

        // Test for list streams for invalid scope.
        final CompletableFuture<Map<String, StreamConfiguration>> completableFuture1 = new CompletableFuture<>();
        completableFuture1.completeExceptionally(StoreException.create(StoreException.Type.DATA_NOT_FOUND, "scope1"));
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(completableFuture1);
        response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Streams response code", 404, response.getStatus());
        response.close();

        // Test for list streams failure.
        final CompletableFuture<Map<String, StreamConfiguration>> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new Exception());
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(completableFuture);
        response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Streams response code", 500, response.getStatus());
        response.close();

        // Test for filtering streams.
        final StreamConfiguration streamConfiguration3 = StreamConfiguration.builder()
                .scalingPolicy(ScalingPolicy.fixed(1))
                .build();
        Map<String, StreamConfiguration> allStreamsList = ImmutableMap.of(stream1, streamConfiguration1, stream2, streamConfiguration2,
                                                                   NameUtils.getInternalNameForStream("stream3"), streamConfiguration3);
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(
                CompletableFuture.completedFuture(allStreamsList));
        response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Streams response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());
        StreamsList streamsListResp = response.readEntity(StreamsList.class);
        assertEquals("List count", 2, streamsListResp.getStreams().size());
        assertEquals("List element", "stream1", streamsListResp.getStreams().get(0).getStreamName());
        assertEquals("List element", "stream2", streamsListResp.getStreams().get(1).getStreamName());
        response.close();

        response = addAuthHeaders(client.target(resourceURI).queryParam("showInternalStreams", "true").request()).buildGet().invoke();
        assertEquals("List Streams response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());
        streamsListResp = response.readEntity(StreamsList.class);
        assertEquals("List count", 1, streamsListResp.getStreams().size());
        assertEquals("List element", NameUtils.getInternalNameForStream("stream3"),
                streamsListResp.getStreams().get(0).getStreamName());
        response.close();

        // Test to list large number of streams.
        streamsList = new HashMap<>();
        for (int i = 0; i < 50000; i++) {
            streamsList.put("stream" + i, streamConfiguration1);
        }
        when(mockControllerService.listStreamsInScope("scope1")).thenReturn(CompletableFuture.completedFuture(streamsList));
        response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Streams response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());
        final StreamsList streamsList2 = response.readEntity(StreamsList.class);
        assertEquals("List count", 50000, streamsList2.getStreams().size());
        response.close();
    }