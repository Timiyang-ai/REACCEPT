public Obs getObs(Integer obsId) throws APIException {
		return context.getDAOContext().getObsDAO().getObs(obsId);
	}