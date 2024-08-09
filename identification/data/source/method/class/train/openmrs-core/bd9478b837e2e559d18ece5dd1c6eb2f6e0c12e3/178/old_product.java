@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public List<Obs> getObservations(String searchString) throws APIException;