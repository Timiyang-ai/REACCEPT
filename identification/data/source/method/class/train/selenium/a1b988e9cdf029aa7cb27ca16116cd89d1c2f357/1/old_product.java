public void validate() {
		if (role==GridRole.WEBDRIVER || role ==GridRole.REMOTE_CONTROL){
			if (registrationURL==null){
				throw new InvalidParameterException("registration url cannot be null");
			}
		}
		
	}