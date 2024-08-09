public Drug saveDrug(Drug drug) throws APIException {
		checkIfLocked();
		
		if (drug.getCreator() == null)
			drug.setCreator(Context.getAuthenticatedUser());
		if (drug.getDateCreated() == null)
			drug.setDateCreated(new Date());
		
		return dao.saveDrug(drug);
	}