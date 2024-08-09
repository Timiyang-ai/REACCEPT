public int compareTo(PersonAttribute other) {
		int retValue = 0;
		retValue = isVoided().compareTo(other.isVoided());
		if (retValue == 0)
			retValue = OpenmrsUtil.compareWithNullAsLatest(getDateCreated(), other.getDateCreated());
		if (retValue == 0)
			retValue = getAttributeType().getPersonAttributeTypeId().compareTo(
			    other.getAttributeType().getPersonAttributeTypeId());
		if (retValue == 0)
			retValue = OpenmrsUtil.compareWithNullAsGreatest(getValue(), other.getValue());
		if (retValue == 0)
			retValue = OpenmrsUtil.compareWithNullAsGreatest(getPersonAttributeId(), other.getPersonAttributeId());
		
		return retValue;
	}