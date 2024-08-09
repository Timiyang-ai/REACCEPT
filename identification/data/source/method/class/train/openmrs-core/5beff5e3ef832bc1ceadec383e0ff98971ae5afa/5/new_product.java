public void handle(Person person, User unvoidingUser, Date origParentVoidedDate, String unused) {
		
		// only operate on voided objects
		if (person.isPersonVoided() && (origParentVoidedDate == null || origParentVoidedDate.equals(person.getPersonDateVoided()))) {
			
			// only unvoid objects that were voided at the same time as the parent object
				person.setPersonVoided(false);
				person.setPersonVoidedBy(null);
				person.setPersonDateVoided(null);
				person.setPersonVoidReason(null);
		}
	}