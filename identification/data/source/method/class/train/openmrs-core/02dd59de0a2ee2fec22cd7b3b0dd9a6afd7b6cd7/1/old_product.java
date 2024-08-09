@Override
	public String getName() {
		if (getPerson() != null) {
			return getPerson().getPersonName().getFullName();
		}
		else {
			return super.getName();
		}
	}