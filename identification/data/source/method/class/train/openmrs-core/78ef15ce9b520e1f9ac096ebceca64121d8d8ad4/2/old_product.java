public void voidObs(Obs obs, String reason) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_OBS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_OBS);
		obs.setVoided(true);
		obs.setVoidReason(reason);
		obs.setVoidedBy(context.getAuthenticatedUser());
		obs.setDateVoided(new Date());
		getObsDAO().updateObs(obs);
	}