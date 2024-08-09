@Override
    public boolean login() throws LoginException {
        if (_callbackHandler == null) {
            throw new LoginException("Missing callback to gather information from the user.");
        }

        NameCallback usernameCallback = new NameCallback("user name: ");
        PasswordCallback passwordCallback = new PasswordCallback("password: ", false);

        Callback[] callbacks = new Callback[2];
        callbacks[0] = usernameCallback;
        callbacks[1] = passwordCallback;

        String username;
        String password;

        try {
            _callbackHandler.handle(callbacks);
            username = usernameCallback.getName();
            password = passwordCallback.getPassword() == null ? "" : new String(passwordCallback.getPassword());
            passwordCallback.clearPassword();
        } catch (IOException e) {
            _log.trace("{}", e);
            throw new LoginException(e.toString());
        } catch (UnsupportedCallbackException e) {
            _log.trace("{}", e);
            throw new LoginException(
                    "Callback {} not available to gather authentication information from the user.".replace("{}", e
                            .getCallback().getClass().getName()));
        }

        IWindowsIdentity windowsIdentity = null;
        try {
            windowsIdentity = _auth.logonUser(username, password);
        } catch (Exception e) {
            _log.trace("{}", e);
            throw new LoginException(e.getMessage());
        }

        try {
            // disable guest login
            if (!_allowGuestLogin && windowsIdentity.isGuest()) {
                _log.debug("guest login disabled: {}", windowsIdentity.getFqn());
                throw new LoginException("Guest login disabled");
            }

            _principals = new LinkedHashSet<Principal>();
            _principals.addAll(getUserPrincipals(windowsIdentity, _principalFormat));
            if (_roleFormat != PrincipalFormat.none) {
                for (IWindowsAccount group : windowsIdentity.getGroups()) {
                    _principals.addAll(getRolePrincipals(group, _roleFormat));
                }
            }

            _username = windowsIdentity.getFqn();
            _log.debug("successfully logged in {} ({})", _username, windowsIdentity.getSidString());
        } finally {
            windowsIdentity.dispose();
        }

        return true;
    }