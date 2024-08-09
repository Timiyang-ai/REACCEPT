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
        
        String username = null;
        String password = null;
        
        try {
            _callbackHandler.handle(callbacks);
            username = usernameCallback.getName();
            password = passwordCallback.getPassword() == null ? "" 
            		: new String(passwordCallback.getPassword());
            passwordCallback.clearPassword();
        } catch (java.io.IOException e) {
            throw new LoginException(e.toString());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException("Callback " + e.getCallback().getClass().getName() +
                    " not available to gather authentication information from the user.");
        }

        IWindowsIdentity windowsIdentity = null;
        try {
        	windowsIdentity = _auth.logonUser(username, password);
        } catch (Exception e) {
        	throw new LoginException(e.getMessage());
        }
        
        _principals = new ArrayList<Principal>();
        _principals.add(new UserPrincipal(windowsIdentity.getFqn()));
        for(IWindowsAccount group : windowsIdentity.getGroups()) {
        	_principals.add(new RolePrincipal(group.getFqn()));
        }
        
        _username = windowsIdentity.getFqn();
        debug("successfully logged in " + _username);
        return true;
	}