public List<PersonAttribute> getAttributes(String attributeName) {
		List<PersonAttribute> ret = new Vector<PersonAttribute>();
		
		for (PersonAttribute attribute : getActiveAttributes()) {
			PersonAttributeType type = attribute.getAttributeType();
			if (type != null && attributeName.equals(type.getName()) && !attribute.isVoided()) {
				ret.add(attribute);
			}
		}
		
		return ret;
	}