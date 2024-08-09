@Override
	public void setAsText(String text) throws IllegalArgumentException {
		UserService es = Context.getUserService();
		if (StringUtils.hasText(text)) {
			try {
				Privilege p = es.getPrivilege(text);
				setValue(p);
				//when a privilege is not found, no exception is generated. throw one to execute the catch block
				if (p == null) {
					throw new Exception();
				}
			}
			catch (Exception ex) {
				Privilege p = es.getPrivilegeByUuid(text);
				setValue(p);
				if (p == null) {
					log.error("Error setting text: " + text, ex);
					throw new IllegalArgumentException("Privilege not found: " + ex.getMessage());
				}
			}
		} else {
			setValue(null);
		}
	}