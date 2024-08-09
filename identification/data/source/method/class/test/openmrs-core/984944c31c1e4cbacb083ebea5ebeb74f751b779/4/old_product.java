@Transactional(readOnly=true)
	public List<PersonAttributeType> getPersonAttributeTypes(String personType, String viewType);