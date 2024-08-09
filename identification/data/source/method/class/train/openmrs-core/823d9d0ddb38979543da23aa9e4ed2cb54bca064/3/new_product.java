public Drug retireDrug(Drug drug, String reason) throws APIException {
			return dao.saveDrug(drug);
	}