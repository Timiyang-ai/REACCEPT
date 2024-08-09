public void validate() {
		if (role == GridRole.WEBDRIVER || role == GridRole.REMOTE_CONTROL) {
			if (registrationURL == null) {
				throw new InvalidParameterException("registration url cannot be null");
			}
		}

		// TODO freyanud : validation should also check that the selenium server
		// param passed to the node do not contain anything that doesn't make
		// sense in a grid environement.For instance launching a node with
		// -interactive.
		if (getNodeRemoteControlConfiguration().isInteractive() == true ){
			throw new InvalidParameterException("no point launching the node in interactive mode");
		}

	}