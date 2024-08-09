@Override
	public OrderGroup voidOrderGroup(OrderGroup group, String voidReason) throws APIException {
		// fail early if this order group is already voided
		if (group.getVoided())
			return group;
		
		if (!StringUtils.hasLength(voidReason))
			throw new IllegalArgumentException("voidReason cannot be empty or null");
		
		group.setVoided(Boolean.TRUE);
		group.setVoidReason(voidReason);
		group.setVoidedBy(Context.getAuthenticatedUser());
		if (group.getDateVoided() == null)
			group.setDateVoided(new Date());
		
		return dao.saveOrderGroup(group);
	}