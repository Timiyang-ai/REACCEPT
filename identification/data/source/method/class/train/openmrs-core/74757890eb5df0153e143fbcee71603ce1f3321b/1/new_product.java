public PersonAttribute getAttribute(Integer attributeTypeId) {
		for (PersonAttribute attribute : getActiveAttributes()) {
			if (attributeTypeId.equals(attribute.getAttributeType().getPersonAttributeTypeId()) && !attribute.isVoided()) {
				return attribute;
			}
		}	
		return null;
	}