@Transactional(readOnly = true)
	public Integer resolveUserId(XCN xcn) throws HL7Exception {
		String idNumber = xcn.getIDNumber().getValue();
		String familyName = xcn.getFamilyName().getSurname().getValue();
		String givenName = xcn.getGivenName().getValue();
		
		// unused
		// String assigningAuthority = xcn.getAssigningAuthority()
		// .getUniversalID().getValue();
		
		/*
		 * if ("null".equals(familyName)) familyName = null; if
		 * ("null".equals(givenName)) givenName = null; if
		 * ("null".equals(assigningAuthority)) assigningAuthority = null;
		 */
		if (idNumber != null && idNumber.length() > 0) {
			// log.debug("searching for user by id " + idNumber);
			try {
				Integer userId = Integer.valueOf(idNumber);
				User user = Context.getUserService().getUser(userId);
				return user.getUserId();
			}
			catch (Exception e) {
				log.error("Invalid user ID '" + idNumber + "'", e);
				return null;
			}
		} else {
			// log.debug("searching for user by name");
			try {
				List<User> users = Context.getUserService().getUsersByName(givenName,familyName,true);
				if( users == null) {
					log.error(getFindingUserErrorMessage(idNumber, familyName, givenName) + ": User not found");
					return null;
				}
				else if( users.size() == 1){
					return users.get(0).getUserId();
				}
				else{
					//Return null if that user ambiguous
					log.error(getFindingUserErrorMessage(idNumber, familyName, givenName) + ": Found " + users.size() + " ambiguous users.");
					return null;
				}
			}
			catch (Exception e) {
				log.error(getFindingUserErrorMessage(idNumber, familyName, givenName), e);
				return null;
			}
		}
	}