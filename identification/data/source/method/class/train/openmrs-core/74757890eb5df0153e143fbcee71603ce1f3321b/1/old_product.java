public PersonAttribute getAttribute(Integer attributeTypeId) {
		for (PersonAttribute attribute : getActiveAttributes()) {
			if (attributeTypeId.equals(attribute.getAttributeType().getPersonAttributeTypeId())) {
				return attribute;
			}
		}
		
		return null;
	}