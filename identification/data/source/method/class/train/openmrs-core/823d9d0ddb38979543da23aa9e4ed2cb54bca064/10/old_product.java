public Drug retireDrug(Drug drug, String reason) throws APIException {
		
		if (drug.isRetired() == false) {
			drug.setRetired(true);
			drug.setRetiredBy(Context.getAuthenticatedUser());
			drug.setRetireReason(reason);
			drug.setDateRetired(new Date());
			return dao.saveDrug(drug);
		}
		
		return drug;
	}