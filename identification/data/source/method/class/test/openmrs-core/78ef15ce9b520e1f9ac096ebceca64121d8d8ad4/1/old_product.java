@Transactional(readOnly = true)
	public Obs getObs(Integer obsId) throws APIException;