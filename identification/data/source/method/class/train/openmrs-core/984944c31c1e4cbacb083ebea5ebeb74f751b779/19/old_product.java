@Deprecated
    @Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_RELATIONSHIPS })
	public List<Relationship> getRelationships(Person p, boolean showVoided) throws APIException;