@Test
    public void testUpdateStreamState() throws Exception {
        final String resourceURI = getURI() + "v1/scopes/scope1/streams/stream1/state";

        // Test to seal a stream.
        when(mockControllerService.sealStream("scope1", "stream1")).thenReturn(CompletableFuture.completedFuture(
                UpdateStreamStatus.newBuilder().setStatus(UpdateStreamStatus.Status.SUCCESS).build()));
        StreamState streamState = new StreamState().streamState(StreamState.StreamStateEnum.SEALED);
        Response response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(streamState)).invoke();
        assertEquals("Update Stream State response code", 200, response.getStatus());
        response.close();

        // Test to seal a non existent scope.
        when(mockControllerService.sealStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                UpdateStreamStatus.newBuilder().setStatus(UpdateStreamStatus.Status.SCOPE_NOT_FOUND).build()));
        streamState = new StreamState().streamState(StreamState.StreamStateEnum.SEALED);
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(streamState)).invoke();
        assertEquals("Update Stream State response code", 404, response.getStatus());
        response.close();

        // Test to seal a non existent stream.
        when(mockControllerService.sealStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                UpdateStreamStatus.newBuilder().setStatus(UpdateStreamStatus.Status.STREAM_NOT_FOUND).build()));
        streamState = new StreamState().streamState(StreamState.StreamStateEnum.SEALED);
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(streamState)).invoke();
        assertEquals("Update Stream State response code", 404, response.getStatus());
        response.close();

        // Test to check failure.
        when(mockControllerService.sealStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                UpdateStreamStatus.newBuilder().setStatus(UpdateStreamStatus.Status.FAILURE).build()));
        streamState = new StreamState().streamState(StreamState.StreamStateEnum.SEALED);
        response = addAuthHeaders(client.target(resourceURI).request()).buildPut(Entity.json(streamState)).invoke();
        assertEquals("Update Stream State response code", 500, response.getStatus());
        response.close();
    }