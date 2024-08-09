@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public Obs getObs(Integer obsId) throws APIException;