public void handle(Person person, User unvoidingUser, Date origParentVoidedDate, String unused) {
		
		// only operate on voided objects
		if (person.isPersonVoided()) {
			
			// only unvoid objects that were voided at the same time as the parent object
			if (origParentVoidedDate == null || origParentVoidedDate.equals(person.getPersonDateVoided())) {
				person.setPersonVoided(false);
				person.setPersonVoidedBy(null);
				person.setPersonDateVoided(null);
				person.setPersonVoidReason(null);
			}
		}
	}