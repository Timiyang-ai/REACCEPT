public boolean equalsContent(PersonName otherName) {
		return new EqualsBuilder().append(defaultString(otherName.getPrefix()), defaultString(prefix)).append(
		    defaultString(otherName.getGivenName()), defaultString(givenName)).append(
		    defaultString(otherName.getMiddleName()), defaultString(middleName)).append(
		    defaultString(otherName.getFamilyNamePrefix()), defaultString(familyNamePrefix)).append(
		    defaultString(otherName.getDegree()), defaultString(degree)).append(defaultString(otherName.getFamilyName()),
		    defaultString(familyName)).append(defaultString(otherName.getFamilyName2()), defaultString(familyName2)).append(
		    defaultString(otherName.getFamilyNameSuffix()), defaultString(familyNameSuffix)).isEquals();
	}