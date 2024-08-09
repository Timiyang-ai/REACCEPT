public int compareTo(PersonAttribute other) {
		int retValue;
		if ((retValue = OpenmrsUtil.compareWithNullAsGreatest(getAttributeType(), other.getAttributeType())) != 0) {
			return retValue;
		}
		
		if ((retValue = isVoided().compareTo(other.isVoided())) != 0) {
			return retValue;
		}
		
		if ((retValue = OpenmrsUtil.compareWithNullAsLatest(getDateCreated(), other.getDateCreated())) != 0) {
			return retValue;
		}
		
		if ((retValue = OpenmrsUtil.compareWithNullAsGreatest(getValue(), other.getValue())) != 0) {
			return retValue;
		}

		return OpenmrsUtil.compareWithNullAsGreatest(getPersonAttributeId(), other.getPersonAttributeId());
	}