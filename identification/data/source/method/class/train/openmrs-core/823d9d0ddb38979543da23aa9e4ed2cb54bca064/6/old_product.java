public Drug unretireDrug(Drug drug) throws APIException {
		if (drug.isRetired() == true) {
			drug.setRetired(false);
			drug.setRetiredBy(null);
			drug.setRetireReason(null);
			drug.setDateRetired(null);
			return dao.saveDrug(drug);
		}
		
		return drug;
	}