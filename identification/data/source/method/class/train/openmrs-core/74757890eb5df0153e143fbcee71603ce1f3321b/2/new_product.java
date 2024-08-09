public PersonAttribute getAttribute(String attributeName) {
		if (attributeName != null)
			for (PersonAttribute attribute : getAttributes()) {
				PersonAttributeType type = attribute.getAttributeType();
				if (type != null && attributeName.equals(type.getName()) && !attribute.isVoided()) {
					return attribute;
				}
			}
		
		return null;
	}