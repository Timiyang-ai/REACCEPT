@Test
    public void testDeleteStream() throws Exception {
        final String resourceURI = getURI() + "v1/scopes/scope1/streams/stream1";

        // Test to delete a sealed stream
        when(mockControllerService.deleteStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                DeleteStreamStatus.newBuilder().setStatus(DeleteStreamStatus.Status.SUCCESS).build()));
        Response response = client.target(resourceURI).request().buildDelete().invoke();
        assertEquals("Delete Stream response code", 204, response.getStatus());
        response.close();

        // Test to delete a unsealed stream
        when(mockControllerService.deleteStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                DeleteStreamStatus.newBuilder().setStatus(DeleteStreamStatus.Status.STREAM_NOT_SEALED).build()));
        response = client.target(resourceURI).request().buildDelete().invoke();
        assertEquals("Delete Stream response code", 412, response.getStatus());
        response.close();

        // Test to delete a non existent stream
        when(mockControllerService.deleteStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                DeleteStreamStatus.newBuilder().setStatus(DeleteStreamStatus.Status.STREAM_NOT_FOUND).build()));
        response = client.target(resourceURI).request().buildDelete().invoke();
        assertEquals("Delete Stream response code", 404, response.getStatus());
        response.close();

        // Test to delete a stream giving an internal server error
        when(mockControllerService.deleteStream(scope1, stream1)).thenReturn(CompletableFuture.completedFuture(
                DeleteStreamStatus.newBuilder().setStatus(DeleteStreamStatus.Status.FAILURE).build()));
        response = client.target(resourceURI).request().buildDelete().invoke();
        assertEquals("Delete Stream response code", 500, response.getStatus());
        response.close();
    }