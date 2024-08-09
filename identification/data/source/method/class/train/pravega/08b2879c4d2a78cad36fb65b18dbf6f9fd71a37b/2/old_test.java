    @Test
    public void registerInterceptors() throws Exception {
        //Test the registration method.
        GRPCServerConfig config = GRPCServerConfigImpl.builder()
                                                      .authorizationEnabled(true)
                                                      .userPasswordFile(file.getAbsolutePath())
                                                      .port(1000)
                                                      .build();

        AuthHandlerManager manager = new AuthHandlerManager(config);
        int port = TestUtils.getAvailableListenPort();
        ServerBuilder<?> server = ServerBuilder.forPort(port).useTransportSecurity(
                new File(SecurityConfigDefaults.TLS_SERVER_CERT_PATH),
                new File(SecurityConfigDefaults.TLS_SERVER_PRIVATE_KEY_PATH));

        server.addService(serviceImpl);
        manager.registerInterceptors(server);
        server.build().start();

        InlineExecutor executor = new InlineExecutor();
        @Cleanup
        final ControllerImpl controllerClient = new ControllerImpl(ControllerImplConfig.builder()
                .clientConfig(ClientConfig.builder()
                                          .controllerURI(URI.create("tcp://localhost:" + port)).build())
                .retryAttempts(1).build(),
                executor);

        //Malformed authorization header.
        assertThrows(AuthenticationException.class, () ->
                manager.authenticateAndAuthorize("hi", "", AuthHandler.Permissions.READ));

        //Non existent interceptor method.
        assertThrows(AuthenticationException.class, () ->
        manager.authenticateAndAuthorize("hi", credentials("invalid", ""), AuthHandler.Permissions.READ));

        //Specify a valid method but malformed parameters for password interceptor.
        assertThrows(IllegalArgumentException.class, () ->
        manager.authenticateAndAuthorize("hi", credentials(AuthConstants.BASIC, ":"), AuthHandler.Permissions.READ));

        //Specify a valid method but incorrect password for password interceptor.
        assertThrows(AuthenticationException.class, () ->
                manager.authenticateAndAuthorize("hi", basic("dummy3", "wrong"), AuthHandler.Permissions.READ));

        //Specify a valid method and parameters but invalid resource for default interceptor.
        assertFalse("Not existent resource should return false",
                manager.authenticateAndAuthorize("invalid", basic("dummy3", "password"), AuthHandler.Permissions.READ));

        //Valid parameters for default interceptor
        assertTrue("Read access for read resource should return true",
                manager.authenticateAndAuthorize("readresource", basic("dummy3", "password"), AuthHandler.Permissions.READ));

        //Stream/scope access should be extended to segment.
        assertTrue("Read access for read resource should return true",
                manager.authenticateAndAuthorize("readresource/segment", basic("dummy3", "password"), AuthHandler.Permissions.READ));

        //Levels of access
        assertFalse("Write access for read resource should return false",
                manager.authenticateAndAuthorize("readresource", basic("dummy3", "password"), AuthHandler.Permissions.READ_UPDATE));

        assertTrue("Read access for write resource should return true",
                manager.authenticateAndAuthorize("totalaccess", basic("dummy3", "password"), AuthHandler.Permissions.READ));

        assertTrue("Write access for write resource should return true",
                manager.authenticateAndAuthorize("totalaccess", basic("dummy3", "password"), AuthHandler.Permissions.READ_UPDATE));

        //Check the wildcard access
        assertTrue("Write access for write resource should return true",
                manager.authenticateAndAuthorize("totalaccess", basic("dummy4", "password"), AuthHandler.Permissions.READ_UPDATE));

        assertTrue("Test handler should be called", manager.authenticateAndAuthorize("any", testHandler(), AuthHandler.Permissions.READ));

        assertThrows(RetriesExhaustedException.class, () -> controllerClient.createScope("hi").join());
    }