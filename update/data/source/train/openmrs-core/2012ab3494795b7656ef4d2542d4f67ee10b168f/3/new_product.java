public void authenticate() {
		if (Context.isAuthenticated() && Context.getAuthenticatedUser().equals(authenticatedUser)) {
			return;
		}
		
		try {
			Context.authenticate("admin", "test");
			authenticatedUser = Context.getAuthenticatedUser();
			return;
		}
		catch (ContextAuthenticationException wrongCredentialsError) {
			if (useInMemoryDatabase()) {
				// if we get here the user is using some database other than the standard
				// in-memory database, prompt the user for input
				log.error("For some reason we couldn't auth as admin:test ?!", wrongCredentialsError);
			}
		}
		
		Integer attempts = 0;
		
		// TODO: how to make this a locale specific message for the user to see?
		String message = null;
		
		// only need to authenticate once per session
		while (!Context.isAuthenticated() && attempts < 3) {
			
			// look in the runtime properties for a defined username and
			// password first
			String junitusername = null;
			String junitpassword = null;
			
			try {
				Properties props = this.getRuntimeProperties();
				junitusername = props.getProperty("junit.username");
				junitpassword = props.getProperty("junit.password");
			}
			catch (Exception e) {
				// if anything happens just default to asking the user
			}
			
			String[] credentials = null;
			
			// ask the user for creds if no junit username/pass defined
			// in the runtime properties or if that username/pass failed already
			if (junitusername == null || junitpassword == null || attempts > 0) {
				credentials = askForUsernameAndPassword(message);
				// credentials are null if the user clicked "cancel" in popup
				if (credentials == null)
					return;
			} else
				credentials = new String[] { junitusername, junitpassword };
			
			// try to authenticate to the Context with either the runtime
			// defined credentials or the user supplied credentials from the
			// popup
			try {
				Context.authenticate(credentials[0], credentials[1]);
				authenticatedUser = Context.getAuthenticatedUser();
			}
			catch (ContextAuthenticationException e) {
				message = "Invalid username/password.  Try again.";
			}
			
			attempts++;
		}
	}