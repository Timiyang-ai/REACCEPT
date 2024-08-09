public synchronized Order saveOrder(Order order, OrderContext orderContext) throws APIException {
		if (order.getOrderId() != null) {
			throw new APIException("Order.cannot.edit.existing");
		}
		if (order.getDateActivated() == null) {
			order.setDateActivated(new Date());
		}
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
			throw new APIException("Order.concept.required");
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
				throw new APIException("Order.type.cannot.determine");
			}
			
			order.setOrderType(orderType);
		}
		if (order.getCareSetting() == null) {
			CareSetting careSetting = null;
			if (orderContext != null) {
				careSetting = orderContext.getCareSetting();
			}
			if (careSetting == null || (previousOrder != null && !careSetting.equals(previousOrder.getCareSetting()))) {
				throw new APIException("Order.care.cannot.determine");
			}
			order.setCareSetting(careSetting);
		}
		
		if (!order.getOrderType().getJavaClass().isAssignableFrom(order.getClass())) {
			throw new APIException("Order.type.class.does.not.match", new Object[] { order.getOrderType().getJavaClass(),
			        order.getClass().getName() });
		}
		
		if (REVISE == order.getAction()) {
			if (previousOrder == null) {
				throw new APIException("Order.previous.required");
			}
			stopOrder(previousOrder, aMomentBefore(order.getDateActivated()));
		} else if (DISCONTINUE == order.getAction()) {
			discontinueExistingOrdersIfNecessary(order);
		}
		
		if (previousOrder != null) {
			//Check that patient, careSetting, concept and drug if is drug order have not changed
			//we need to use a SQL query to by pass the hibernate cache
			boolean isPreviousDrugOrder = DrugOrder.class.isAssignableFrom(previousOrder.getClass());
			List<Object[]> rows = dao.getOrderFromDatabase(previousOrder, isPreviousDrugOrder);
			Object[] rowData = rows.get(0);
			if (!rowData[0].equals(previousOrder.getPatient().getPatientId())) {
				throw new APIException("Order.cannot.change.patient");
			} else if (!rowData[1].equals(previousOrder.getCareSetting().getCareSettingId())) {
				throw new APIException("Order.cannot.change.careSetting");
			} else if (!rowData[2].equals(previousOrder.getConcept().getConceptId())) {
				throw new APIException("Order.cannot.change.concept");
			} else if (isPreviousDrugOrder) {
				Drug previousDrug = ((DrugOrder) previousOrder).getDrug();
				if (previousDrug == null && rowData[3] != null) {
					throw new APIException("Order.cannot.change.drug");
				} else if (previousDrug != null && !OpenmrsUtil.nullSafeEquals(rowData[3], previousDrug.getDrugId())) {
					throw new APIException("Order.cannot.change.drug");
				}
			}
			
			//concept should be the same as on previous order, same applies to drug for drug orders
			boolean isDrugOrderAndHasADrug = isDrugOrder && ((DrugOrder) order).getDrug() != null;
			if (!OpenmrsUtil.nullSafeEquals(order.getConcept(), previousOrder.getConcept())) {
				throw new APIException("Order.previous.concept");
			} else if (isDrugOrderAndHasADrug) {
				DrugOrder drugOrder1 = (DrugOrder) order;
				DrugOrder drugOrder2 = (DrugOrder) previousOrder;
				if (!OpenmrsUtil.nullSafeEquals(drugOrder1.getDrug(), drugOrder2.getDrug())) {
					throw new APIException("Order.previous.drug");
				}
			} else if (!order.getOrderType().equals(previousOrder.getOrderType())) {
				throw new APIException("Order.type.does.not.match");
			} else if (!order.getCareSetting().equals(previousOrder.getCareSetting())) {
				throw new APIException("Order.care.setting.does.not.match");
			} else if (!getActualType(order).equals(getActualType(previousOrder))) {
				throw new APIException("Order.class.does.not.match");
			}
		}
		
		if (DISCONTINUE != order.getAction()) {
 			List<Order> activeOrders = getActiveOrders(order.getPatient(), null, order.getCareSetting(), null);
			List<String> parallelOrders = Collections.emptyList();
			if(orderContext!=null && orderContext.getAttribute(PARALLEL_ORDERS)!=null){
				parallelOrders= Arrays.asList((String[])orderContext.getAttribute(PARALLEL_ORDERS));
			}
 			for (Order activeOrder : activeOrders) {
 				//Reject if there is an active drug order for the same orderable with overlapping schedule
				if (!parallelOrders.contains(activeOrder.getUuid()) && areDrugOrdersOfSameOrderableAndOverlappingSchedule(order, activeOrder)) {
					throw new AmbiguousOrderException("Order.cannot.have.more.than.one");
 				}
 			}
 		}
		
		return saveOrderInternal(order, orderContext);
	}