public Drug saveDrug(Drug drug) throws APIException {
		checkIfLocked();
		
		return dao.saveDrug(drug);
	}