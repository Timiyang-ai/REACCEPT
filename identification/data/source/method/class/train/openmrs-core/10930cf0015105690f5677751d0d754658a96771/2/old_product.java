public String getFullName() {
		List<String> temp = new ArrayList<String>();
		if (getPrefix() != null)
			temp.add(getPrefix());
		if (getGivenName() != null)
			temp.add(getGivenName());
		if (getMiddleName() != null)
			temp.add(getMiddleName());
		if (getFamilyNamePrefix() != null)
			temp.add(getFamilyNamePrefix());
		if (getFamilyName() != null)
			temp.add(getFamilyName());
		if (getFamilyName2() != null)
			temp.add(getFamilyName2());
		if (getFamilyNameSuffix() != null)
			temp.add(getFamilyNameSuffix());
		if (getDegree() != null)
			temp.add(getDegree());
		
		String nameString = StringUtils.collectionToDelimitedString(temp, " ");
		
		return nameString.trim();
	}