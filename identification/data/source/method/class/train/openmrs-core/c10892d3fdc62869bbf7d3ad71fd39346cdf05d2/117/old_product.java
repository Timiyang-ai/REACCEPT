@Authorized( { OpenmrsConstants.PRIV_ADD_OBS, OpenmrsConstants.PRIV_EDIT_OBS })
	public Obs saveObs(Obs obs, String changeMessage) throws APIException;