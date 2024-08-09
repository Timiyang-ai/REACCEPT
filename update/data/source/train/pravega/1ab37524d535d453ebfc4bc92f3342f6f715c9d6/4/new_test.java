@Test
    public void testCreateScope() throws ExecutionException, InterruptedException {
        final CreateScopeRequest createScopeRequest = new CreateScopeRequest().scopeName(scope1);
        final String resourceURI = getURI() + "v1/scopes/";

        // Test to create a new scope.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.SUCCESS).build()));
        Response response = addAuthHeaders(client.target(resourceURI).request()).buildPost(Entity.json(createScopeRequest)).invoke();
        assertEquals("Create Scope response code", 201, response.getStatus());
        response.close();

        // Test to create an existing scope.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.SCOPE_EXISTS).build()));
        response = addAuthHeaders(client.target(resourceURI).request()).buildPost(Entity.json(createScopeRequest)).invoke();
        assertEquals("Create Scope response code", 409, response.getStatus());
        response.close();

        // create scope failure.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.FAILURE).build()));
        response = addAuthHeaders(client.target(resourceURI).request()).buildPost(Entity.json(createScopeRequest)).invoke();
        assertEquals("Create Scope response code", 500, response.getStatus());
        response.close();

        // Test to create an invalid scope name.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.SCOPE_EXISTS).build()));
        createScopeRequest.setScopeName("_system");
        response = addAuthHeaders(client.target(resourceURI).request()).buildPost(Entity.json(createScopeRequest)).invoke();
        assertEquals("Create Scope response code", 400, response.getStatus());
        response.close();
    }