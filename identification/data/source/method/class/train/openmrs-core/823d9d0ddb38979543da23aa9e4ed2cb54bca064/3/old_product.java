public Drug retireDrug(Drug drug, String reason) throws APIException {
		
		if (drug.isRetired() == false) {
			drug.setRetired(true);
			drug.setRetireReason(reason);
			return dao.saveDrug(drug);
		}
		
		return drug;
	}