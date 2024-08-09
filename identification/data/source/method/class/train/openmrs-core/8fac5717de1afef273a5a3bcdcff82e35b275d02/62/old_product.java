@Authorized( { OpenmrsConstants.PRIV_DELETE_RELATIONSHIPS })
	public Relationship voidRelationship(Relationship relationship, String voidReason) throws APIException;