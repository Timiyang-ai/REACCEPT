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
            throw new LoginException("Callback " + e.getCallback().toString() +
                    " not available to gather authentication information from the user.");
        }
        
        IWindowsIdentity windowsIdentity = _auth.logonUser(username, password);
        
        _principals = new HashSet<Principal>();
        _principals.add(new UserPrincipal(windowsIdentity.getFqn()));
        for(IWindowsAccount group : windowsIdentity.getGroups()) {
        	_principals.add(new RolePrincipal(group.getFqn()));
        }
        
        System.out.println("logged in: " + windowsIdentity.getFqn());
        return true;
	}