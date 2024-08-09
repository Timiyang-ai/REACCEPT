@Override
	public synchronized String getNewOrderNumber() {
		GlobalProperty globalProperty = Context.getAdministrationService().getGlobalPropertyObject(
		    OpenmrsConstants.GLOBAL_PROPERTY_NEXT_ORDER_NUMBER);
		if (globalProperty == null) {
			globalProperty = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_NEXT_ORDER_NUMBER,
			        ORDER_NUMBER_START_VALUE, "The next order number available for assignment");
		}
		
		String gpTextValue = globalProperty.getPropertyValue();
		if (!StringUtils.hasText(gpTextValue)) {
			throw new APIException("Invalid value for global property named: "
			        + OpenmrsConstants.GLOBAL_PROPERTY_NEXT_ORDER_NUMBER);
		}
		
		Long gpNumericValue = null;
		try {
			gpNumericValue = Long.parseLong(gpTextValue);
		}
		catch (NumberFormatException ex) {
			throw new APIException("Invalid value for global property named: "
			        + OpenmrsConstants.GLOBAL_PROPERTY_NEXT_ORDER_NUMBER);
		}
		
		String orderNumber = ORDER_NUMBER_PREFIX + gpTextValue;
		
		globalProperty.setPropertyValue(String.valueOf(gpNumericValue + 1));
		
		Context.addProxyPrivilege(PrivilegeConstants.MANAGE_GLOBAL_PROPERTIES);
		try {
			Context.getAdministrationService().saveGlobalProperty(globalProperty);
		}
		finally {
			Context.removeProxyPrivilege(PrivilegeConstants.MANAGE_GLOBAL_PROPERTIES);
		}
		
		return orderNumber;
	}