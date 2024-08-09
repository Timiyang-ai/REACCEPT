public Order saveOrder(Order order, OrderContext orderContext) throws APIException {
		if (order.getOrderId() != null) {
			throw new APIException("Cannot edit an existing order, you need to revise it instead");
		}
		
		if (order.getOrderType() == null) {
			OrderType orderType = getOrderTypeByConcept(order.getConcept());
			if (orderType == null && orderContext != null) {
				orderType = orderContext.getOrderType();
			}
			if (orderType == null) {
				throw new APIException("No order type matches the concept class");
			}
			order.setOrderType(orderType);
		}
		if (order.getCareSetting() == null) {
			if (orderContext != null && orderContext.getCareSetting() != null) {
				order.setCareSetting(orderContext.getCareSetting());
			} else {
				throw new APIException("CareSetting is required for an order");
			}
		}
		
		if (Order.Action.REVISE.equals(order.getAction())) {
			Order previousOrder = order.getPreviousOrder();
			if (previousOrder == null) {
				throw new APIException("Previous Order is required for a revised order");
			}
			stopOrder(previousOrder, order.getStartDate());
		} else if (Order.Action.DISCONTINUE.equals(order.getAction())) {
			discontinueExistingOrdersIfNecessary(order);
		}
		
		if (order.getPreviousOrder() != null) {
			Order previousOrder = order.getPreviousOrder();
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
				throw new APIException("The concept of the previous order and the new revised order don't match");
			} else if (isDrugOrderAndHasADrug) {
				DrugOrder drugOrder1 = (DrugOrder) order;
				DrugOrder drugOrder2 = (DrugOrder) previousOrder;
				if (!OpenmrsUtil.nullSafeEquals(drugOrder1.getDrug(), drugOrder2.getDrug())) {
					throw new APIException("The drug of the previous order and the new revised order don't match");
				}
			}
		}
		
		return saveOrderInternal(order, orderContext);
	}