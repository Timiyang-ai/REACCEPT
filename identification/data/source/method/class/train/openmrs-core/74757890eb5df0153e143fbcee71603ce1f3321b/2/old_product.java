public PersonAttribute getAttribute(String attributeName) {
		if (attributeName != null)
			for (PersonAttribute attribute : getActiveAttributes()) {
				PersonAttributeType type = attribute.getAttributeType();
				if (type != null && attributeName.equals(type.getName())) {
					return attribute;
				}
			}
		
		return null;
	}