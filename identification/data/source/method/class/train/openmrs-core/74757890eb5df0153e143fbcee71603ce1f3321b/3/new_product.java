public List<PersonAttribute> getAttributes(Integer attributeTypeId) {
		List<PersonAttribute> ret = new Vector<PersonAttribute>();
		
		for (PersonAttribute attribute : getActiveAttributes()) {
			if (attributeTypeId.equals(attribute.getAttributeType().getPersonAttributeTypeId()) && !attribute.isVoided()) {
				ret.add(attribute);
			}
		}
		
		return ret;
	}