@Test
    public void testGetScope() throws ExecutionException, InterruptedException {
        final String resourceURI = getURI() + "v1/scopes/scope1";
        final String resourceURI2 = getURI() + "v1/scopes/scope2";

        // Test to get existent scope
        when(mockControllerService.getScope(scope1)).thenReturn(CompletableFuture.completedFuture("scope1"));
        Response response = client.target(resourceURI).request().buildGet().invoke();
        assertEquals("Get existent scope", 200, response.getStatus());
        response.close();

        // Test to get non-existent scope
        when(mockControllerService.getScope("scope2")).thenReturn(CompletableFuture.supplyAsync(() -> {
            throw StoreException.create(StoreException.Type.DATA_NOT_FOUND, "scope2");
        }));
        response = client.target(resourceURI2).request().buildGet().invoke();
        assertEquals("Get non existent scope", 404, response.getStatus());
        response.close();

        //Test for get scope failure.
        final CompletableFuture<String> completableFuture2 = new CompletableFuture<>();
        completableFuture2.completeExceptionally(new Exception());
        when(mockControllerService.getScope(scope1)).thenReturn(completableFuture2);
        response = client.target(resourceURI).request().buildGet().invoke();
        assertEquals("Get scope fail test", 500, response.getStatus());
        response.close();
    }