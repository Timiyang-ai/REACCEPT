@Test
    public void testListScopes() throws ExecutionException, InterruptedException {
        final String resourceURI = getURI() + "v1/scopes";

        // Test to list scopes.
        List<String> scopesList = Arrays.asList("scope1", "scope2", "scope3");
        when(mockControllerService.listScopes()).thenReturn(CompletableFuture.completedFuture(scopesList));
        Response response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Scopes response code", 200, response.getStatus());
        assertTrue(response.bufferEntity());

        verifyScopes(response.readEntity(ScopesList.class));

        response.close();

        // Test for list scopes failure.
        final CompletableFuture<List<String>> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new Exception());
        when(mockControllerService.listScopes()).thenReturn(completableFuture);
        response = addAuthHeaders(client.target(resourceURI).request()).buildGet().invoke();
        assertEquals("List Scopes response code", 500, response.getStatus());
        response.close();
    }