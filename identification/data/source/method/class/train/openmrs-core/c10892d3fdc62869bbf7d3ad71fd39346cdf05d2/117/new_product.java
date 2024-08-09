@Authorized( { PrivilegeConstants.ADD_OBS, PrivilegeConstants.EDIT_OBS })
	public Obs saveObs(Obs obs, String changeMessage) throws APIException;