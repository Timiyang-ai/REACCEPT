@Override
	public void handle(Person person, User voidingUser, Date voidedDate, String voidReason) {
		
		// skip over all work if the object is already voided
		if (!person.isPersonVoided()) {
			if (person.getPersonId() != null) {
				// Skip if person is not persisted
				UserService us = Context.getUserService();
				for (User user : us.getUsersByPerson(person, false)) {
					us.retireUser(user, voidReason);
				}
			}
			
			person.setPersonVoided(true);
			person.setPersonVoidReason(voidReason);
			
			if (person.getPersonVoidedBy() == null) {
				person.setPersonVoidedBy(voidingUser);
			}
			if (person.getPersonDateVoided() == null) {
				person.setPersonDateVoided(voidedDate);
			}
		}
	}