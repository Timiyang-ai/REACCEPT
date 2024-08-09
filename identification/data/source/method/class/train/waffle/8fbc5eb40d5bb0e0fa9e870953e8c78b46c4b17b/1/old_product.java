private boolean negotiate(final Request request, final HttpServletResponse response,
            final AuthorizationHeader authorizationHeader) {

        final String securityPackage = authorizationHeader.getSecurityPackage();
        // maintain a connection-based session for NTLM tokens
        final String connectionId = NtlmServletRequest.getConnectionId(request);

        this.log.debug("security package: {}, connection id: {}", securityPackage, connectionId);

        final boolean ntlmPost = authorizationHeader.isNtlmType1PostAuthorizationHeader();

        if (ntlmPost) {
            // type 1 NTLM authentication message received
            this.auth.resetSecurityToken(connectionId);
        }

        // log the user in using the token
        IWindowsSecurityContext securityContext;

        try {
            final byte[] tokenBuffer = authorizationHeader.getTokenBytes();
            this.log.debug("token buffer: {} byte(s)", Integer.valueOf(tokenBuffer.length));
            securityContext = this.auth.acceptSecurityToken(connectionId, tokenBuffer, securityPackage);
            this.log.debug("continue required: {}", Boolean.valueOf(securityContext.isContinue()));

            final byte[] continueTokenBytes = securityContext.getToken();
            if (continueTokenBytes != null && continueTokenBytes.length > 0) {
                final String continueToken = BaseEncoding.base64().encode(continueTokenBytes);
                this.log.debug("continue token: {}", continueToken);
                response.addHeader("WWW-Authenticate", securityPackage + " " + continueToken);
            }

            if (securityContext.isContinue() || ntlmPost) {
                response.setHeader("Connection", "keep-alive");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                response.flushBuffer();
                return false;
            }

        } catch (final IOException e) {
            this.log.warn("error logging in user: {}", e.getMessage());
            this.log.trace("", e);
            this.sendUnauthorized(response);
            return false;
        }

        // create and register the user principal with the session
        final IWindowsIdentity windowsIdentity = securityContext.getIdentity();

        // disable guest login
        if (!this.allowGuestLogin && windowsIdentity.isGuest()) {
            this.log.warn("guest login disabled: {}", windowsIdentity.getFqn());
            this.sendUnauthorized(response);
            return false;
        }

        try {

            this.log.debug("logged in user: {} ({})", windowsIdentity.getFqn(), windowsIdentity.getSidString());

            final GenericWindowsPrincipal windowsPrincipal = new GenericWindowsPrincipal(windowsIdentity,
                    this.principalFormat, this.roleFormat);

            this.log.debug("roles: {}", windowsPrincipal.getRolesString());

            // create a session associated with this request if there's none
            final HttpSession session = request.getSession(true);
            this.log.debug("session id: {}", session == null ? "null" : session.getId());

            this.register(request, response, windowsPrincipal, securityPackage, windowsPrincipal.getName(), null);
            this.log.info("successfully logged in user: {}", windowsPrincipal.getName());

        } finally {
            windowsIdentity.dispose();
        }

        return true;
    }