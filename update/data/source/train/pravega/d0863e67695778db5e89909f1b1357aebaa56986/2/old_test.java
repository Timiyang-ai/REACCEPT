@Test
    public void testDeleteScope() throws ExecutionException, InterruptedException {
        final String resourceURI = "v1/scopes/scope1";

        // Test to delete a scope.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.SUCCESS));
        response = target(resourceURI).request().async().delete();
        assertEquals("Delete Scope response code", 204, response.get().getStatus());

        // Test to delete scope with existing streams.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.SCOPE_NOT_EMPTY));
        response = target(resourceURI).request().async().delete();
        assertEquals("Delete Scope response code", 412, response.get().getStatus());

        // Test to delete non-existing scope.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.SCOPE_NOT_FOUND));
        response = target(resourceURI).request().async().delete();
        assertEquals("Delete Scope response code", 404, response.get().getStatus());

        // Test delete scope failure.
        when(mockControllerService.deleteScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                DeleteScopeStatus.FAILURE));
        response = target(resourceURI).request().async().delete();
        assertEquals("Delete Scope response code", 500, response.get().getStatus());
    }