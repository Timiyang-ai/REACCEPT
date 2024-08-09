@Override
	public OrderGroup voidOrderGroup(OrderGroup group, String voidReason) throws APIException {
		// fail early if this order group is already voided
		if (group.getVoided())
			return group;
		
		if (!StringUtils.hasLength(voidReason))
			throw new IllegalArgumentException("voidReason cannot be empty or null");
		
		return dao.saveOrderGroup(group);
	}