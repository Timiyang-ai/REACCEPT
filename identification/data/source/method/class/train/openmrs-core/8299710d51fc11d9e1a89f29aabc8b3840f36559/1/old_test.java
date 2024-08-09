@Test
	public void cloneForRevision_shouldSetAllTheRelevantFields() throws Exception {
		OrderTest.assertThatAllFieldsAreCopied(new DrugOrder(), "cloneForRevision", "creator", "dateCreated", "action",
		    "changedBy", "dateChanged", "voided", "dateVoided", "voidedBy", "voidReason", "encounter", "orderNumber",
		    "orderer", "previousOrder", "startDate", "dateStopped");
	}