@Test
    public void testAcceptSecurityToken() {
        final String securityPackage = "Negotiate";
        final String targetName = "localhost";
        IWindowsCredentialsHandle clientCredentials = null;
        WindowsSecurityContextImpl clientContext = null;
        IWindowsSecurityContext serverContext = null;
        try {
            // client credentials handle
            clientCredentials = WindowsCredentialsHandleImpl.getCurrent(securityPackage);
            clientCredentials.initialize();
            // initial client security context
            clientContext = new WindowsSecurityContextImpl();
            clientContext.setPrincipalName(WindowsAccountImpl.getCurrentUsername());
            clientContext.setCredentialsHandle(clientCredentials.getHandle());
            clientContext.setSecurityPackage(securityPackage);
            clientContext.initialize(null, null, targetName);
            // accept on the server
            final WindowsAuthProviderImpl provider = new WindowsAuthProviderImpl();
            final String connectionId = "testConnection-" + Thread.currentThread().getId();
            do {
                // accept the token on the server
                try {
                    serverContext = provider.acceptSecurityToken(connectionId, clientContext.getToken(),
                            securityPackage);
                } catch (final Exception e) {
                    WindowsAuthProviderTests.LOGGER.error("{}", e);
                    break;
                }

                if (serverContext != null && serverContext.isContinue()) {
                    // initialize on the client
                    final SecBufferDesc continueToken = new SecBufferDesc(Sspi.SECBUFFER_TOKEN,
                            serverContext.getToken());
                    clientContext.initialize(clientContext.getHandle(), continueToken, targetName);
                    WindowsAuthProviderTests.LOGGER.debug("Token: {}",
                            BaseEncoding.base64().encode(serverContext.getToken()));
                }

            } while (clientContext.isContinue() || serverContext != null && serverContext.isContinue());

            if (serverContext != null) {
                Assertions.assertThat(serverContext.getIdentity().getFqn().length()).isGreaterThan(0);

                WindowsAuthProviderTests.LOGGER.debug(serverContext.getIdentity().getFqn());
                for (final IWindowsAccount group : serverContext.getIdentity().getGroups()) {
                    WindowsAuthProviderTests.LOGGER.debug(" {}", group.getFqn());
                }
            }
        } finally {
            if (serverContext != null) {
                serverContext.dispose();
            }
            if (clientContext != null) {
                clientContext.dispose();
            }
            if (clientCredentials != null) {
                clientCredentials.dispose();
            }
        }
    }