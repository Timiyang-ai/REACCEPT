public void voidObs(Obs obs, String reason) throws APIException {
		obs.setVoided(true);
		obs.setVoidReason(reason);
		obs.setVoidedBy(context.getAuthenticatedUser());
		obs.setDateVoided(new Date());
		updateObs(obs);
	}