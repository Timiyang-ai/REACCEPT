public Order saveOrder(Order order, OrderContext orderContext) throws APIException {
		if (order.getOrderId() != null) {
			throw new APIException("Cannot edit an existing order, you need to revise it instead");
		}
		if (order.getStartDate() == null) {
			order.setStartDate(new Date());
		}
		Order previousOrder = order.getPreviousOrder();
		if (order.getOrderType() == null) {
			OrderType orderType = null;
			if (orderContext != null) {
				orderType = orderContext.getOrderType();
			}
			if (orderType == null) {
				orderType = getOrderTypeByConcept(order.getConcept());
			}
			//this order's order type should match that of the previous
			if (orderType == null || (previousOrder != null && !orderType.equals(previousOrder.getOrderType()))) {
				throw new APIException("Cannot determine the order type of the order");
			}
			order.setOrderType(orderType);
		}
		if (order.getCareSetting() == null) {
			CareSetting careSetting = null;
			if (orderContext != null) {
				careSetting = orderContext.getCareSetting();
			}
			if (careSetting == null || (previousOrder != null && !careSetting.equals(previousOrder.getCareSetting()))) {
				throw new APIException("Cannot determine the care setting of the order");
			}
			order.setCareSetting(careSetting);
		}
		
		if (REVISE == order.getAction()) {
			if (previousOrder == null) {
				throw new APIException("Previous Order is required for a revised order");
			}
			stopOrder(previousOrder, order.getStartDate());
		} else if (DISCONTINUE == order.getAction()) {
			discontinueExistingOrdersIfNecessary(order);
		}
		
		if (previousOrder != null) {
			//Check that patient, careSetting, concept and drug if is drug order have not changed
			//we need to use a SQL query to by pass the hibernate cache
			String query = "SELECT patient_id, care_setting, concept_id FROM orders WHERE order_id = ";
			boolean isDrugOrder = DrugOrder.class.isAssignableFrom(previousOrder.getClass());
			if (isDrugOrder) {
				query = "SELECT o.patient_id, o.care_setting, o.concept_id, d.drug_inventory_id "
				        + "FROM orders o, drug_order d WHERE o.order_id = d.order_id AND o.order_id =";
			}
			List<List<Object>> rows = Context.getAdministrationService()
			        .executeSQL(query + previousOrder.getOrderId(), true);
			List<Object> rowData = rows.get(0);
			if (!rowData.get(0).equals(previousOrder.getPatient().getPatientId())) {
				throw new APIException("Cannot change the patient of an order");
			} else if (!rowData.get(1).equals(previousOrder.getCareSetting().getCareSettingId())) {
				throw new APIException("Cannot change the careSetting of an order");
			} else if (!rowData.get(2).equals(previousOrder.getConcept().getConceptId())) {
				throw new APIException("Cannot change the concept of an order");
			} else if (isDrugOrder && !rowData.get(3).equals(((DrugOrder) previousOrder).getDrug().getDrugId())) {
				throw new APIException("Cannot change the drug of a drug order");
			}
			
			//concept should be the same as on previous order, same applies to drug for drug orders
			boolean isDrugOrderAndHasADrug = DrugOrder.class.isAssignableFrom(order.getClass())
			        && ((DrugOrder) order).getDrug() != null;
			if (!order.getConcept().equals(previousOrder.getConcept())) {
				throw new APIException("The concept of the previous order and the new one order don't match");
			} else if (isDrugOrderAndHasADrug) {
				DrugOrder drugOrder1 = (DrugOrder) order;
				DrugOrder drugOrder2 = (DrugOrder) previousOrder;
				if (!OpenmrsUtil.nullSafeEquals(drugOrder1.getDrug(), drugOrder2.getDrug())) {
					throw new APIException("The drug of the previous order and the new one order don't match");
				}
			} else if (!order.getOrderType().equals(previousOrder.getOrderType())) {
				throw new APIException("The order type does not match that of the previous order");
			} else if (!order.getCareSetting().equals(previousOrder.getCareSetting())) {
				throw new APIException("The care setting does not match that of the previous order");
			} else if (!getActualType(order).equals(getActualType(previousOrder))) {
				throw new APIException("The class does not match that of the previous order");
			}
		}
		if (order.getConcept() == null && DrugOrder.class.isAssignableFrom(order.getClass())) {
			order.setConcept(((DrugOrder) order).getDrug().getConcept());
		}
		
		return saveOrderInternal(order, orderContext);
	}