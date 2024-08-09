@Test
    public void testGetStream() throws ExecutionException, InterruptedException {
        when(mockControllerService.getStreamStore()).thenReturn(mockStreamStore);

        // Test to get an existing stream
        when(mockStreamStore.getConfiguration(scope1, stream1)).thenReturn(streamConfigFuture);
        response = target(resourceURI).request().async().get();
        streamResponseActual = response.get().readEntity(StreamProperty.class);
        assertEquals("Get Stream Config Status", 200, response.get().getStatus());
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);

        // Get a non-existent stream
        when(mockStreamStore.getConfiguration(scope1, stream2)).thenReturn(CompletableFuture.supplyAsync(() -> {
            throw new DataNotFoundException("Stream Not Found");
        }));
        response = target(resourceURI2).request().async().get();
        streamResponseActual = response.get().readEntity(StreamProperty.class);
        assertEquals("Get Stream Config Status", 404, response.get().getStatus());
    }