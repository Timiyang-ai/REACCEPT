public boolean authenticateUser(String eid, UserEdit edit, String password) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("authenticateUser(String " + eid + ", UserEdit " + edit
					+ ", String password)");
		}
		if (eid == null || "null".equalsIgnoreCase(eid) || "".equals(eid)) {
			// maybe should throw exception instead?
			// since I assume I am in a chain, I will be quiet about it
			LOG.debug("eid == null");
			return false;
		}
		final AuthInfo authInfo = nakamuraAuthenticationHelper
				.getPrincipalLoggedIntoNakamura(getHttpServletRequest());
		if (authInfo != null) {
			if (eid.equalsIgnoreCase(authInfo.getPrincipal())) {
				edit.setFirstName(authInfo.getFirstName());
				edit.setLastName(authInfo.getLastName());
				edit.setEmail(authInfo.getEmailAddress());
				return true;
			}
		}
		return false;
	}