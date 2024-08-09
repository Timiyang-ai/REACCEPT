public Drug saveDrug(Drug drug) throws APIException {
		checkIfLocked();
		
		ValidateUtil.validate(drug);
		return dao.saveDrug(drug);
	}