public List<PersonAttribute> getAttributes(String attributeName) {
		List<PersonAttribute> attributes = new Vector<PersonAttribute>();
		
		for (PersonAttribute attribute : getActiveAttributes()) {
			PersonAttributeType type = attribute.getAttributeType();
			if (type != null && attributeName.equals(type.getName())) {
				attributes.add(attribute);
			}
		}
		
		return attributes;
	}