public Order saveOrder(Order order) throws APIException {
		
		//if new order, fill the order number and version.
		if (order.getOrderId() == null) {
			order.setOrderNumber(getNewOrderNumber());
			order.setOrderVersion(1);
			order.setLatestVersion(true);
			
			return validateAndSaveOrder(order);
			
		} else {
			
			//TODO Do we discontinue this order?
			dao.setOrderLatestVersion(order.getOrderId(), false);
			Context.evictFromSession(order); //Any retrievals for this order should get the database state.
			
			Order newOrder = order.copy();
			newOrder.setOrderVersion(order.getOrderVersion() + 1);
			newOrder.setOrderNumber(order.getOrderNumber());
			newOrder.setLatestVersion(true);
			
			//Setting date created to null, so that our magical machinery can be sure to assign exactly the same Date to all objects created at the same instant even if the millisecond rolls over.
			newOrder.setDateCreated(null);
			
			newOrder.setDateChanged(null);
			newOrder.setChangedBy(null);
			newOrder.setUuid(UUID.randomUUID().toString());
			
			return validateAndSaveOrder(newOrder);
		}
	}