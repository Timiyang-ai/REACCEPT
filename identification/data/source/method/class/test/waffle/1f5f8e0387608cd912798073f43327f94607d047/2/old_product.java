@Override
    public boolean login() throws LoginException {
        if (this.callbackHandler == null) {
            throw new LoginException("Missing callback to gather information from the user.");
        }

        NameCallback usernameCallback = new NameCallback("user name: ");
        PasswordCallback passwordCallback = new PasswordCallback("password: ", false);

        Callback[] callbacks = new Callback[2];
        callbacks[0] = usernameCallback;
        callbacks[1] = passwordCallback;

        String userName;
        String password;

        try {
            this.callbackHandler.handle(callbacks);
            userName = usernameCallback.getName();
            password = passwordCallback.getPassword() == null ? "" : new String(passwordCallback.getPassword());
            passwordCallback.clearPassword();
        } catch (IOException e) {
            LOGGER.trace("{}", e);
            throw new LoginException(e.toString());
        } catch (UnsupportedCallbackException e) {
            LOGGER.trace("{}", e);
            throw new LoginException(
                    "Callback {} not available to gather authentication information from the user.".replace("{}", e
                            .getCallback().getClass().getName()));
        }

        IWindowsIdentity windowsIdentity = null;
        try {
            windowsIdentity = this.auth.logonUser(userName, password);
        } catch (Exception e) {
            LOGGER.trace("{}", e);
            throw new LoginException(e.getMessage());
        }

        try {
            // disable guest login
            if (!this.allowGuestLogin && windowsIdentity.isGuest()) {
                LOGGER.debug("guest login disabled: {}", windowsIdentity.getFqn());
                throw new LoginException("Guest login disabled");
            }

            this.principals = new LinkedHashSet<Principal>();
            this.principals.addAll(getUserPrincipals(windowsIdentity, this.principalFormat));
            if (this.roleFormat != PrincipalFormat.none) {
                for (IWindowsAccount group : windowsIdentity.getGroups()) {
                    this.principals.addAll(getRolePrincipals(group, this.roleFormat));
                }
            }

            this.username = windowsIdentity.getFqn();
            LOGGER.debug("successfully logged in {} ({})", this.username, windowsIdentity.getSidString());
        } finally {
            windowsIdentity.dispose();
        }

        return true;
    }