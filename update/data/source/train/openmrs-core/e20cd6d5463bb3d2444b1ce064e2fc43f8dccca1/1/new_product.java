public synchronized Order saveOrder(Order order, OrderContext orderContext) throws APIException {
		if (order.getOrderId() != null) {
			throw new APIException("Order.cannot.edit.existing", (Object[]) null);
		}
		if (order.getDateActivated() == null) {
			order.setDateActivated(new Date());
		}
		//Reject if there is an active order for the same orderable with overlapping schedule
		boolean isDrugOrder = DrugOrder.class.isAssignableFrom(getActualType(order));
		Concept concept = order.getConcept();
		if (concept == null && isDrugOrder) {
			DrugOrder drugOrder = (DrugOrder) order;
			if (drugOrder.getDrug() != null) {
				concept = drugOrder.getDrug().getConcept();
				drugOrder.setConcept(concept);
			}
		}
		if (isDrugOrder) {
			((DrugOrder) order).setAutoExpireDateBasedOnDuration();
		}
		
		if (concept == null) {
			throw new APIException("Order.concept.required", (Object[]) null);
		}
		
		Order previousOrder = order.getPreviousOrder();
		if (order.getOrderType() == null) {
			OrderType orderType = null;
			if (orderContext != null) {
				orderType = orderContext.getOrderType();
			}
			if (orderType == null) {
				orderType = getOrderTypeByConcept(concept);
			}
			//Check if it is instance of DrugOrder
			if (orderType == null && order instanceof DrugOrder) {
				orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.DRUG_ORDER_TYPE_UUID);
			}
			//Check if it is an instance of TestOrder
			if (orderType == null && order instanceof TestOrder) {
				orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.TEST_ORDER_TYPE_UUID);
			}
			
			//this order's order type should match that of the previous
			if (orderType == null || (previousOrder != null && !orderType.equals(previousOrder.getOrderType()))) {
				throw new APIException("Order.type.cannot.determine", (Object[]) null);
			}
			
			order.setOrderType(orderType);
		}
		if (order.getCareSetting() == null) {
			CareSetting careSetting = null;
			if (orderContext != null) {
				careSetting = orderContext.getCareSetting();
			}
			if (careSetting == null || (previousOrder != null && !careSetting.equals(previousOrder.getCareSetting()))) {
				throw new APIException("Order.care.cannot.determine", (Object[]) null);
			}
			order.setCareSetting(careSetting);
		}
		
		if (!order.getOrderType().getJavaClass().isAssignableFrom(order.getClass())) {
			throw new APIException("Order.type.class.does.not.match", new Object[] { order.getOrderType().getJavaClass(),
			        order.getClass().getName() });
		}
		
		if (REVISE == order.getAction()) {
			if (previousOrder == null) {
				throw new APIException("Order.previous.required", (Object[]) null);
			}
			stopOrder(previousOrder, aMomentBefore(order.getDateActivated()));
		} else if (DISCONTINUE == order.getAction()) {
			discontinueExistingOrdersIfNecessary(order);
		}
		
		if (previousOrder != null) {
			//Check that patient, careSetting, concept and drug if is drug order have not changed
			//we need to use a SQL query to by pass the hibernate cache
			boolean isPreviousDrugOrder = DrugOrder.class.isAssignableFrom(previousOrder.getClass());
			List<List<Object>> rows = dao.getOrderFromDatabase(previousOrder, isPreviousDrugOrder);
			List<Object> rowData = rows.get(0);
			if (!rowData.get(0).equals(previousOrder.getPatient().getPatientId())) {
				throw new APIException("Order.cannot.change.patient", (Object[]) null);
			} else if (!rowData.get(1).equals(previousOrder.getCareSetting().getCareSettingId())) {
				throw new APIException("Order.cannot.change.careSetting", (Object[]) null);
			} else if (!rowData.get(2).equals(previousOrder.getConcept().getConceptId())) {
				throw new APIException("Order.cannot.change.concept", (Object[]) null);
			} else if (isPreviousDrugOrder) {
				Drug previousDrug = ((DrugOrder) previousOrder).getDrug();
				if (previousDrug == null && rowData.get(3) != null) {
					throw new APIException("Order.cannot.change.drug", (Object[]) null);
				} else if (previousDrug != null && !OpenmrsUtil.nullSafeEquals(rowData.get(3), previousDrug.getDrugId())) {
					throw new APIException("Order.cannot.change.drug", (Object[]) null);
				}
			}
			
			//concept should be the same as on previous order, same applies to drug for drug orders
			boolean isDrugOrderAndHasADrug = isDrugOrder && ((DrugOrder) order).getDrug() != null;
			if (!OpenmrsUtil.nullSafeEquals(order.getConcept(), previousOrder.getConcept())) {
				throw new APIException("Order.previous.concept", (Object[]) null);
			} else if (isDrugOrderAndHasADrug) {
				DrugOrder drugOrder1 = (DrugOrder) order;
				DrugOrder drugOrder2 = (DrugOrder) previousOrder;
				if (!OpenmrsUtil.nullSafeEquals(drugOrder1.getDrug(), drugOrder2.getDrug())) {
					throw new APIException("Order.previous.drug", (Object[]) null);
				}
			} else if (!order.getOrderType().equals(previousOrder.getOrderType())) {
				throw new APIException("Order.type.does.not.match", (Object[]) null);
			} else if (!order.getCareSetting().equals(previousOrder.getCareSetting())) {
				throw new APIException("Order.care.setting.does.not.match", (Object[]) null);
			} else if (!getActualType(order).equals(getActualType(previousOrder))) {
				throw new APIException("Order.class.does.not.match", (Object[]) null);
			}
		}
		
		if (DISCONTINUE != order.getAction()) {
			List<Order> activeOrders = getActiveOrders(order.getPatient(), null, order.getCareSetting(), null);
			for (Order activeOrder : activeOrders) {
				if (order.hasSameOrderableAs(activeOrder)
				        && !OpenmrsUtil.nullSafeEquals(order.getPreviousOrder(), activeOrder)
				        && OrderUtil.checkScheduleOverlap(order, activeOrder)) {
					throw new APIException("Order.cannot.have.more.than.one", (Object[]) null);
				}
			}
		}
		
		return saveOrderInternal(order, orderContext);
	}