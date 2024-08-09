@Test
    public void testGetStream() throws ExecutionException, InterruptedException {
        String resourceURI = getURI() + "v1/scopes/" + scope1 + "/streams/" + stream1;
        String resourceURI2 = getURI() + "v1/scopes/" + scope1 + "/streams/" + stream2;

        // Test to get an existing stream
        when(mockControllerService.getStream(scope1, stream1)).thenReturn(streamConfigFuture);
        Response response = client.target(resourceURI).request().buildGet().invoke();
        assertEquals("Get Stream Config Status", 200, response.getStatus());
        StreamProperty streamResponseActual = response.readEntity(StreamProperty.class);
        testExpectedVsActualObject(streamResponseExpected, streamResponseActual);
        response.close();

        // Get a non-existent stream
        when(mockControllerService.getStream(scope1, stream2)).thenReturn(CompletableFuture.supplyAsync(() -> {
            throw StoreException.create(StoreException.Type.DATA_NOT_FOUND, stream2);
        }));
        response = client.target(resourceURI2).request().buildGet().invoke();
        assertEquals("Get Stream Config Status", 404, response.getStatus());
        response.close();
    }