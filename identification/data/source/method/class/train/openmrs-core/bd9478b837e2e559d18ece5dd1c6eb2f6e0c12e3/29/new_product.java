public void unvoidObs(Obs obs) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_OBS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_OBS);
		obs.setVoided(false);
		obs.setVoidReason(null);
		obs.setVoidedBy(null);
		obs.setDateVoided(new Date());
		updateObs(obs);
	}