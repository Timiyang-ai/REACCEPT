public void createCoord(String coordId, String coordName, String coordEmail)
			throws EntityAlreadyExistsException, InvalidParametersException {

		Assumption.assertNotNull(ERROR_NULL_PARAMETER, coordId);
		Assumption.assertNotNull(ERROR_NULL_PARAMETER, coordName);
		Assumption.assertNotNull(ERROR_NULL_PARAMETER, coordEmail);

		verifyAdminLoggedIn();

		CoordData coordToAdd = new CoordData(coordId, coordName, coordEmail);
		
		if (!coordToAdd.isValid()) {
			throw new InvalidParametersException(coordToAdd.getInvalidStateInfo());
		}
		
		AccountsStorage.inst().getDb().createCoord(coordToAdd);
	}