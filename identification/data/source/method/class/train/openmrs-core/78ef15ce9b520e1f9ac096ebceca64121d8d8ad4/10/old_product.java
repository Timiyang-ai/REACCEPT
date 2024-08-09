public void voidObs(Obs obs, String reason) throws APIException {
		context.getDAOContext().getObsDAO().voidObs(obs, reason);
	}