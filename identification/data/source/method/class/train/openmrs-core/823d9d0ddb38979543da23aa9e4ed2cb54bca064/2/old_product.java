public Drug unretireDrug(Drug drug) throws APIException {
		if (drug.isRetired() == true) {
			drug.setRetired(false);
			return dao.saveDrug(drug);
		}
		
		return drug;
	}