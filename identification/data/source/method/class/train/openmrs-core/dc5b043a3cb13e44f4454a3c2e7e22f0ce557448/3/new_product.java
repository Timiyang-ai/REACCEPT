public boolean voidedPersonAttributeExists(String value) {
		PersonAttribute personAttribute = getPersonAttribute(getPersonAttributeList(QUERY_ALL_VOIDED_PERSON_ATTRIBUTES),
		    value);
		if (personAttribute != null) {
			return personAttribute.getVoided();
		}
		return false;
	}