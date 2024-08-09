@Override
	public void handle(Person person, User creator, Date dateCreated, String other) {
		
		// address collection
		if (person.getAddresses() != null && !person.getAddresses().isEmpty()) {
			for (PersonAddress pAddress : person.getAddresses()) {
				if (pAddress.isBlank()){
					person.removeAddress(pAddress);
					continue;
				}
				pAddress.setPerson(person);
			}
		}
		
		// name collection
		if (person.getNames() != null && !person.getNames().isEmpty()) {
			for (PersonName pName : person.getNames()) {
				pName.setPerson(person);
			}
		}
		
		// attribute collection
		if (person.getAttributes() != null && !person.getAttributes().isEmpty()) {
			for (PersonAttribute pAttr : person.getAttributes()) {
				pAttr.setPerson(person);
			}
		}
		
		//if the patient was marked as dead and reversed, drop the cause of death
		if (!person.isDead() && person.getCauseOfDeath() != null) {
			person.setCauseOfDeath(null);
		}
		
		// do the checks for voided attributes (also in PersonVoidHandler)
		if (person.isPersonVoided()) {
			
			if (!StringUtils.hasLength(person.getPersonVoidReason())) {
				throw new APIException("Person.voided.bit", new Object[] { person });
			}
			
			if (person.getPersonVoidedBy() == null) {
				person.setPersonVoidedBy(creator);
			}
			if (person.getPersonDateVoided() == null) {
				person.setPersonDateVoided(dateCreated);
			}
		} else {
			// voided is set to false
			person.setPersonVoidedBy(null);
			person.setPersonDateVoided(null);
			person.setPersonVoidReason(null);
		}
	}