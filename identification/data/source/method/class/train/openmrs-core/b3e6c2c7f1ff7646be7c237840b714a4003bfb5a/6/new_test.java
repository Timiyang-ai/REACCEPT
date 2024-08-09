	@Test
	public void cloneForRevision_shouldSetAllTheRelevantFields() throws Exception {
		DrugOrder drugOrder = new DrugOrder();
		Drug drug = new Drug();
		drug.setConcept(new Concept());
		drugOrder.setDrug(drug);
		OrderTest.assertThatAllFieldsAreCopied(drugOrder, "cloneForRevision", "creator", "dateCreated", "action",
		    "changedBy", "dateChanged", "voided", "dateVoided", "voidedBy", "voidReason", "encounter", "orderNumber",
		    "orderer", "previousOrder", "dateActivated", "dateStopped", "accessionNumber");
	}