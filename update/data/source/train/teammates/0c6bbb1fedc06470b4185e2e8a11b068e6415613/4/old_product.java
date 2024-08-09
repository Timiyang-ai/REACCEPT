public void createCoord(String coordId, String coordName, String coordEmail)
			throws EntityAlreadyExistsException, InvalidParametersException {

		verifyAdminLoggedIn();

		CoordData coordToAdd = new CoordData(coordId, coordName, coordEmail);
		
		if (!coordToAdd.isValid()) {
			throw new InvalidParametersException(coordToAdd.getInvalidStateInfo());
		}
		
		AccountsStorage.inst().getDb().createCoord(coordToAdd);
	}