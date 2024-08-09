public void handle(Person person, User voidingUser, Date voidedDate, String voidReason) {
		
		// skip over all work if the object is already voided
		if (!person.isPersonVoided()) {
			
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