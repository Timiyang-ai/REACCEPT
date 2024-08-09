@Test
    public void testCreateScope() throws ExecutionException, InterruptedException {
        final CreateScopeRequest createScopeRequest = new CreateScopeRequest().scopeName(scope1);
        final String resourceURI = "v1/scopes/";

        // Test to create a new scope.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.SUCCESS).build()));
        response = target(resourceURI).request().async().post(Entity.json(createScopeRequest));
        assertEquals("Create Scope response code", 201, response.get().getStatus());

        // Test to create an existing scope.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.SCOPE_EXISTS).build()));
        response = target(resourceURI).request().async().post(Entity.json(createScopeRequest));
        assertEquals("Create Scope response code", 409, response.get().getStatus());

        // create scope failure.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.FAILURE).build()));
        response = target(resourceURI).request().async().post(Entity.json(createScopeRequest));
        assertEquals("Create Scope response code", 500, response.get().getStatus());

        // Test to create an invalid scope name.
        when(mockControllerService.createScope(scope1)).thenReturn(CompletableFuture.completedFuture(
                CreateScopeStatus.newBuilder().setStatus(CreateScopeStatus.Status.SCOPE_EXISTS).build()));
        createScopeRequest.setScopeName("_system");
        response = target(resourceURI).request().async().post(Entity.json(createScopeRequest));
        assertEquals("Create Scope response code", 400, response.get().getStatus());
    }