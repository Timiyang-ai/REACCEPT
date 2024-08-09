public void unvoidObs(Obs obs) throws APIException {
		obs.setVoided(false);
		obs.setVoidReason(null);
		obs.setVoidedBy(null);
		obs.setDateVoided(new Date());
		updateObs(obs);
	}