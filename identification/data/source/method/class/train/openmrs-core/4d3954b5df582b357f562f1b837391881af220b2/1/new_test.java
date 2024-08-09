	@Test
	public void copy_shouldCopyAllDrugOrderFields() throws Exception {
		DrugOrder drugOrder = new DrugOrder();
		Drug drug = new Drug();
		drug.setConcept(new Concept());
		drugOrder.setDrug(drug);
		
		OrderTest.assertThatAllFieldsAreCopied(drugOrder, null);
		
	}