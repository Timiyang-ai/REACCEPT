@Override
	public String getName() {
		if (getPerson() != null && getPerson().getPersonName() != null) {
			return getPerson().getPersonName().getFullName();
		} else {
			log.warn("We no longer support providers who are not linked to person. Set the name on the linked person");
			return null;
		}
	}