	@Test(expected = APIException.class)
	public void getConceptIdForUnits_shouldFailIfUnitsIsNotSpecified() throws IOException {
		Database1_9_7UpgradeIT.createOrderEntryUpgradeFileWithTestData("mg=540" + "\n" + "ounces=5402");
		
		UpgradeUtil.getConceptIdForUnits("drug_order_quantity_units");
	}