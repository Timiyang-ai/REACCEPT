@Test
    public void testNegotiate() throws IOException, ServletException {
        final String securityPackage = NegotiateSecurityFilterTests.NEGOTIATE;
        // client credentials handle
        IWindowsCredentialsHandle clientCredentials = null;
        WindowsSecurityContextImpl clientContext = null;
        // role will contain both Everyone and SID
        this.filter.setRoleFormat("both");
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
            // filter chain
            final SimpleFilterChain filterChain = new SimpleFilterChain();
            // negotiate
            boolean authenticated = false;
            final SimpleHttpRequest request = new SimpleHttpRequest();
            while (true) {
                final String clientToken = Base64.getEncoder().encodeToString(clientContext.getToken());
                request.addHeader("Authorization", securityPackage + " " + clientToken);

                final SimpleHttpResponse response = new SimpleHttpResponse();
                this.filter.doFilter(request, response, filterChain);

                final Subject subject = (Subject) request.getSession(false).getAttribute("javax.security.auth.subject");
                authenticated = subject != null && subject.getPrincipals().size() > 0;

                if (authenticated) {
                    assertThat(response.getHeaderNamesSize()).isGreaterThanOrEqualTo(0);
                    break;
                }

                Assertions.assertTrue(response.getHeader("WWW-Authenticate").startsWith(securityPackage + " "));
                Assertions.assertEquals("keep-alive", response.getHeader("Connection"));
                Assertions.assertEquals(2, response.getHeaderNamesSize());
                Assertions.assertEquals(401, response.getStatus());
                final String continueToken = response.getHeader("WWW-Authenticate")
                        .substring(securityPackage.length() + 1);
                final byte[] continueTokenBytes = Base64.getDecoder().decode(continueToken);
                assertThat(continueTokenBytes.length).isGreaterThan(0);
                final SecBufferDesc continueTokenBuffer = new SecBufferDesc(Sspi.SECBUFFER_TOKEN, continueTokenBytes);
                clientContext.initialize(clientContext.getHandle(), continueTokenBuffer, "localhost");
            }
            Assertions.assertTrue(authenticated);
            Assertions.assertTrue(filterChain.getRequest() instanceof NegotiateRequestWrapper);
            Assertions.assertTrue(filterChain.getResponse() instanceof SimpleHttpResponse);
            final NegotiateRequestWrapper wrappedRequest = (NegotiateRequestWrapper) filterChain.getRequest();
            Assertions.assertEquals(NegotiateSecurityFilterTests.NEGOTIATE.toUpperCase(), wrappedRequest.getAuthType());
            Assertions.assertEquals(Secur32Util.getUserNameEx(EXTENDED_NAME_FORMAT.NameSamCompatible),
                    wrappedRequest.getRemoteUser());
            Assertions.assertTrue(wrappedRequest.getUserPrincipal() instanceof WindowsPrincipal);
            final String everyoneGroupName = Advapi32Util.getAccountBySid("S-1-1-0").name;
            Assertions.assertTrue(wrappedRequest.isUserInRole(everyoneGroupName));
            Assertions.assertTrue(wrappedRequest.isUserInRole("S-1-1-0"));
        } finally {
            if (clientContext != null) {
                clientContext.dispose();
            }
            if (clientCredentials != null) {
                clientCredentials.dispose();
            }
        }
    }