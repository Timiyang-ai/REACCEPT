public PersonName getPersonName() {
		if (getNames() != null && names.size() > 0) {
			return (PersonName) names.toArray()[0];
		} else {
			return new PersonName();
		}
	}