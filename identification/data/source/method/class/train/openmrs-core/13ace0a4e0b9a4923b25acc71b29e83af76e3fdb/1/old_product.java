public void handle(Order order, User creator, Date dateCreated, String other) {
		if (order.getPatient() == null && order.getEncounter() != null)
			order.setPatient(order.getEncounter().getPatient());
		if (order.getOrderNumber() == null) {
			synchronized (OrderSaveHandler.class) {
				if (orderNumber == null) {
					orderNumber = 0;
					try {
						Context.addProxyPrivilege(PrivilegeConstants.SQL_LEVEL_ACCESS);
						List<List<Object>> rowObjects = Context.getAdministrationService().executeSQL(
						    "SELECT max(order_id) FROM orders", true);
						if (CollectionUtils.isNotEmpty(rowObjects) && CollectionUtils.isNotEmpty(rowObjects.get(0))
						        && rowObjects.get(0).get(0) != null)
							orderNumber = (Integer) rowObjects.get(0).get(0);
					}
					finally {
						Context.removeProxyPrivilege(PrivilegeConstants.SQL_LEVEL_ACCESS);
					}
				}
				
				orderNumber++;
			}
			
			String nextOrderNumber = Context.getAdministrationService().getGlobalProperty(
			    OpenmrsConstants.GP_ORDER_ENTRY_ORDER_NUMBER_PREFIX, OpenmrsConstants.ORDER_NUMBER_DEFAULT_PERFIX)
			        + orderNumber.toString();
			order.setOrderNumber(nextOrderNumber);
		}
	}