public String getFullName() {
		List<String> temp = new ArrayList<String>();
		if (StringUtils.hasText(getPrefix()))
			temp.add(getPrefix());
		if (StringUtils.hasText(getGivenName()))
			temp.add(getGivenName());
		if (StringUtils.hasText(getMiddleName()))
			temp.add(getMiddleName());
		if (StringUtils.hasText(getFamilyNamePrefix()))
			temp.add(getFamilyNamePrefix());
		if (StringUtils.hasText(getFamilyName()))
			temp.add(getFamilyName());
		if (StringUtils.hasText(getFamilyName2()))
			temp.add(getFamilyName2());
		if (StringUtils.hasText(getFamilyNameSuffix()))
			temp.add(getFamilyNameSuffix());
		if (StringUtils.hasText(getDegree()))
			temp.add(getDegree());
		
		String nameString = StringUtils.collectionToDelimitedString(temp, " ");
		
		return nameString.trim();
	}