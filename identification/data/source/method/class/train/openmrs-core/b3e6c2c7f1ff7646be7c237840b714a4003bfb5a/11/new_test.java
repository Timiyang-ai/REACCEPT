	@Test
	public void cloneForRevision_shouldSetAllTheRelevantFields() throws Exception {
		TestOrder newTestOrder = new TestOrder();
		
		OrderGroup orderGroup = new OrderGroup();
		newTestOrder.setOrderGroup(orderGroup);
		
		TestOrder revisedTestOrder = newTestOrder.cloneForRevision();
		
		OrderTest.assertThatAllFieldsAreCopied(revisedTestOrder, "cloneForRevision", "creator", "dateCreated", "action",
		    "changedBy", "dateChanged", "voided", "dateVoided", "voidedBy", "voidReason", "encounter", "orderNumber",
		    "orderer", "previousOrder", "dateActivated", "dateStopped", "accessionNumber");
	}