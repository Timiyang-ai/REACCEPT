@Test
    public void testDeleteScope() throws ExecutionException, InterruptedException {
        final String resourceURI = getURI() + "v1/scopes/scope1";

        // Test to delete a scope.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.newBuilder().setStatus(DeleteScopeStatus.Status.SUCCESS).build()));
        Response response = addAuthHeaders(client.target(resourceURI).request()).buildDelete().invoke();
        assertEquals("Delete Scope response code", 204, response.getStatus());
        response.close();

        // Test to delete scope with existing streams.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.newBuilder().setStatus(DeleteScopeStatus.Status.SCOPE_NOT_EMPTY).build()));
        response = addAuthHeaders(client.target(resourceURI).request()).buildDelete().invoke();
        assertEquals("Delete Scope response code", 412, response.getStatus());
        response.close();

        // Test to delete non-existing scope.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.newBuilder().setStatus(DeleteScopeStatus.Status.SCOPE_NOT_FOUND).build()));
        response = addAuthHeaders(client.target(resourceURI).request()).buildDelete().invoke();
        assertEquals("Delete Scope response code", 404, response.getStatus());
        response.close();

        // Test delete scope failure.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.newBuilder().setStatus(DeleteScopeStatus.Status.FAILURE).build()));
        response = addAuthHeaders(client.target(resourceURI).request()).buildDelete().invoke();
        assertEquals("Delete Scope response code", 500, response.getStatus());
        response.close();
    }