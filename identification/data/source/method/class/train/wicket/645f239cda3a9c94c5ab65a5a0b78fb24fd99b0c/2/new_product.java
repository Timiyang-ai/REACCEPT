private boolean login(String username)
	{
		((MySession)Session.get()).setUsername(username);
		continueToOriginalDestination();
		setResponsePage(Application.get().getHomePage());
		return true;
	}