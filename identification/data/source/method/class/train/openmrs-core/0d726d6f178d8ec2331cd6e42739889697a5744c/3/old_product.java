public void setAsText(String text) throws IllegalArgumentException {
		UserService es = Context.getUserService();
		if (StringUtils.hasText(text)) {
			try {
				setValue(es.getPrivilege(text));
			}
			catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Role not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}