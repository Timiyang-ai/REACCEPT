public void unvoidObs(Obs obs) throws APIException {
		context.getDAOContext().getObsDAO().unvoidObs(obs);
	}