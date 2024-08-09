@Transactional(readOnly=true)
	@Authorized({"Manage Relationships"})
	public List<Relationship> getRelationships(Person p, boolean showVoided)
			throws APIException;