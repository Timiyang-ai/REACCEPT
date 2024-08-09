public void setAsText(String text) throws IllegalArgumentException {
		UserService es = Context.getUserService();
		if (StringUtils.hasText(text)) {
			try {
				Role r = es.getRole(text);
				setValue(r);
				//when a role is not found, no exception is generated. throw one to execute the catch block
				if (r == null)
					throw new Exception();
			}
			catch (Exception ex) {
				Role r = es.getRoleByUuid(text);
				setValue(r);
				if (r == null) {
					log.error("Error setting text: " + text, ex);
					throw new IllegalArgumentException("Role not found: " + ex.getMessage());
				}
			}
		} else {
			setValue(null);
		}
	}