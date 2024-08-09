@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_OBS })
	public Obs getComplexObs(Integer obsId, String view) throws APIException;