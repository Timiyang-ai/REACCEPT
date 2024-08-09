	@Test
	public void getAutoExpireDate_shouldInferAutoExpireDateForAKnownSNOMEDCTDurationUnit() throws ParseException {
		DrugOrder drugOrder = new DrugOrder();
		drugOrder.setDateActivated(createDateTime("2014-07-01 10:00:00"));
		drugOrder.setDuration(30);
		drugOrder.setDurationUnits(createUnits(Duration.SNOMED_CT_SECONDS_CODE));
		Date autoExpireDate = new SimpleDosingInstructions().getAutoExpireDate(drugOrder);
		assertEquals(createDateTime("2014-07-01 10:00:29"), autoExpireDate);
	}