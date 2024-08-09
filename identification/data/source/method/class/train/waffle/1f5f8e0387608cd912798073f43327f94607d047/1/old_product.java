@Override
    public boolean login() throws LoginException {
        if (this.callbackHandler == null) {
            throw new LoginException("Missing callback to gather information from the user.");
        }

        final NameCallback usernameCallback = new NameCallback("user name: ");
        final PasswordCallback passwordCallback = new PasswordCallback("password: ", false);

        final Callback[] callbacks = new Callback[2];
        callbacks[0] = usernameCallback;
        callbacks[1] = passwordCallback;

        final String userName;
        final String password;

        try {
            this.callbackHandler.handle(callbacks);
            userName = usernameCallback.getName();
            password = passwordCallback.getPassword() == null ? "" : new String(passwordCallback.getPassword());
            passwordCallback.clearPassword();
        } catch (final IOException e) {
            WindowsLoginModule.LOGGER.trace("", e);
            throw new LoginException(e.toString());
        } catch (final UnsupportedCallbackException e) {
            WindowsLoginModule.LOGGER.trace("", e);
            throw new LoginException(
                    "Callback {} not available to gather authentication information from the user.".replace("{}", e
                            .getCallback().getClass().getName()));
        }

        IWindowsIdentity windowsIdentity;
        try {
            windowsIdentity = this.auth.logonUser(userName, password);
        } catch (final Exception e) {
            WindowsLoginModule.LOGGER.trace("", e);
            throw new LoginException(e.getMessage());
        }

        try {
            // disable guest login
            if (!this.allowGuestLogin && windowsIdentity.isGuest()) {
                WindowsLoginModule.LOGGER.debug("guest login disabled: {}", windowsIdentity.getFqn());
                throw new LoginException("Guest login disabled");
            }

            this.principals = new LinkedHashSet<>();
            this.principals.addAll(WindowsLoginModule.getUserPrincipals(windowsIdentity, this.principalFormat));
            if (this.roleFormat != PrincipalFormat.NONE) {
                for (final IWindowsAccount group : windowsIdentity.getGroups()) {
                    this.principals.addAll(WindowsLoginModule.getRolePrincipals(group, this.roleFormat));
                }
            }

            this.username = windowsIdentity.getFqn();
            WindowsLoginModule.LOGGER.debug("successfully logged in {} ({})", this.username,
                    windowsIdentity.getSidString());
        } finally {
            windowsIdentity.dispose();
        }

        return true;
    }