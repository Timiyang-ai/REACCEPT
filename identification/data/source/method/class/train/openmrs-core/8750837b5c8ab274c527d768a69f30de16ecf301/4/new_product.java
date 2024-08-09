public Integer resolveUserId(String[] xcn) throws HL7Exception {
		// TODO: properly handle family and given names. For now I'm treating givenName+familyName as a username.
		String idNumber = xcn[0];
		String familyName = null;
		String givenName = null;
		String assigningAuthority = null;
		if (xcn.length >= 2) {
			familyName = xcn[1];
		}
		if (xcn.length >= 3) {
			givenName = xcn[2];
		}
		if (xcn.length >= 9) {
			assigningAuthority = xcn[8];
		}
		if (idNumber != null && idNumber.length() > 0) {
			// log.debug("searching for user by id " + idNumber);
			try {
				Integer userId = new Integer(idNumber);
				User u = context.getUserService().getUser(userId);
				return u.getUserId();
			} catch (Exception ex) {
				log.error("Error handling ID Number component '" + idNumber + "' of XCN.", ex);
				return null;
			}
		} else {
			// log.debug("searching for user by name");
			try {
				StringBuilder username = new StringBuilder();
				if (familyName != null) {
					username.append(familyName);
				}
				if (givenName != null) {
					username.append(givenName);
				}
				User u = context.getUserService().getUserByUsername(username.toString());
				return u.getUserId();
			} catch (Exception ex) {
				log.error("Error handling family name '" + familyName + "' and given name '" + givenName + "' components of XCN.", ex);
				for (int i = 0; i < xcn.length; ++i) {
					log.error("xcn[" + i + "]\t" + xcn[i]);	
				}
				return null;
			}
		}
	}