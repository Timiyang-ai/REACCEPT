@Test
    public void testNegotiate() {
        final String securityPackage = "Negotiate";
        IWindowsCredentialsHandle clientCredentials = null;
        WindowsSecurityContextImpl clientContext = null;
        try {
            // client credentials handle
            clientCredentials = WindowsCredentialsHandleImpl.getCurrent(securityPackage);
            clientCredentials.initialize();
            // initial client security context
            clientContext = new WindowsSecurityContextImpl();
            clientContext.setPrincipalName(WindowsAccountImpl.getCurrentUsername());
            clientContext.setCredentialsHandle(clientCredentials);
            clientContext.setSecurityPackage(securityPackage);
            clientContext.initialize(null, null, WindowsAccountImpl.getCurrentUsername());
            // negotiate
            boolean authenticated = false;
            final SimpleHttpRequest request = new SimpleHttpRequest();
            while (true) {
                final String clientToken = BaseEncoding.base64().encode(clientContext.getToken());
                request.addHeader("Authorization", securityPackage + " " + clientToken);

                final SimpleHttpResponse response = new SimpleHttpResponse();
                authenticated = this.authenticator.authenticate(request, response);

                if (authenticated) {
                    Assert.assertNotNull(request.getUserPrincipal());
                    Assert.assertTrue(request.getUserPrincipal() instanceof GenericWindowsPrincipal);
                    final GenericWindowsPrincipal windowsPrincipal = (GenericWindowsPrincipal) request
                            .getUserPrincipal();
                    Assert.assertTrue(windowsPrincipal.getSidString().startsWith("S-"));
                    Assertions.assertThat(windowsPrincipal.getSid().length).isGreaterThan(0);
                    Assert.assertTrue(windowsPrincipal.getGroups().containsKey("Everyone"));
                    Assertions.assertThat(response.getHeaderNames().size()).isLessThanOrEqualTo(1);
                    break;
                }

                Assert.assertTrue(response.getHeader("WWW-Authenticate").startsWith(securityPackage + " "));
                Assert.assertEquals("keep-alive", response.getHeader("Connection"));
                Assert.assertEquals(2, response.getHeaderNames().size());
                Assert.assertEquals(401, response.getStatus());
                final String continueToken = response.getHeader("WWW-Authenticate").substring(
                        securityPackage.length() + 1);
                final byte[] continueTokenBytes = BaseEncoding.base64().decode(continueToken);
                Assertions.assertThat(continueTokenBytes.length).isGreaterThan(0);
                final SecBufferDesc continueTokenBuffer = new SecBufferDesc(Sspi.SECBUFFER_TOKEN, continueTokenBytes);
                clientContext.initialize(clientContext.getHandle(), continueTokenBuffer,
                        WindowsAccountImpl.getCurrentUsername());
            }
            Assert.assertTrue(authenticated);
        } finally {
            if (clientContext != null) {
                clientContext.dispose();
            }
            if (clientCredentials != null) {
                clientCredentials.dispose();
            }
        }
    }