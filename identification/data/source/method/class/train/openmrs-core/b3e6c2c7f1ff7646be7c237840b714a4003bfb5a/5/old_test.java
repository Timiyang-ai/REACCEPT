	@Test
	public void cloneForRevision_shouldSetAllTheRelevantFields() throws Exception {
		Order newOrder = new Order();
		
		OrderGroup orderGroup = new OrderGroup();
		newOrder.setOrderGroup(orderGroup);
		
		Order revisedOrder = newOrder.cloneForRevision();
		
		assertThatAllFieldsAreCopied(revisedOrder, "cloneForRevision", "creator", "dateCreated", "action", "changedBy",
		    "dateChanged", "voided", "dateVoided", "voidedBy", "voidReason", "encounter", "orderNumber", "orderer",
		    "previousOrder", "dateActivated", "dateStopped", "accessionNumber");
	}