public Integer resolveUserId(XCN xcn) throws HL7Exception {
		// TODO: properly handle family and given names. For now I'm treating
		// givenName+familyName as a username.
		String idNumber = xcn.getIDNumber().getValue();
		String familyName = xcn.getFamilyName().getSurname().getValue();
		String givenName = xcn.getGivenName().getValue();
		String assigningAuthority = xcn.getAssigningAuthority()
				.getUniversalID().getValue();
		/*
		if ("null".equals(familyName))
			familyName = null;
		if ("null".equals(givenName))
			givenName = null;
		if ("null".equals(assigningAuthority))
			assigningAuthority = null;
		*/
		if (idNumber != null && idNumber.length() > 0) {
			//log.debug("searching for user by id " + idNumber);
			try {
				Integer userId = new Integer(idNumber);
				User user = context.getUserService().getUser(userId);
				return user.getUserId();
			} catch (Exception e) {
				log.error("Invalid user ID '" + idNumber + "'", e);
				return null;
			}
		} else {
			//log.debug("searching for user by name");
			try {
				StringBuilder username = new StringBuilder();
				if (familyName != null) {
					username.append(familyName);
				}
				if (givenName != null) {
					if (username.length() > 0)
						username.append(" "); // separate names with a space
					username.append(givenName);
				}
				//log.debug("looking for username '" + username + "'");
				User user = context.getUserService().getUserByUsername(
						username.toString());
				return user.getUserId();
			} catch (Exception e) {
				log.error("Error resolving user with family name '"
						+ familyName + "' and given name '" + givenName + "'",
						e);
				return null;
			}
		}
	}