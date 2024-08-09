public List<PersonAttribute> getAttributes(Integer attributeTypeId) {
		List<PersonAttribute> attributes = new Vector<PersonAttribute>();
		
		for (PersonAttribute attribute : getActiveAttributes()) {
			if (attributeTypeId.equals(attribute.getPersonAttributeId())) {
				attributes.add(attribute);
			}
		}
		
		return attributes;
	}