public void voidObs(Obs obs, String reason) throws APIException {
		getObsDAO().voidObs(obs, reason);
	}