	@Test
	public void test_isEligible_PreviousLevel_missingCandidate()
	{
		//
		// Setup dunning context
		final Date dunningDate = TimeUtil.getDay(2012, 02, 01);
		final PlainDunningContext context = createPlainDunningContext(dunningDate, dunningLevel2_20);

		final IDunnableDoc sourceDoc = mkDunnableDocBuilder()
				.setDaysDue(25) // daysDue, suitable for level 2
				.setC_Currency_ID(currencyEUR.getRepoId())
				.setTotalAmt(new BigDecimal("100"))
				.setOpenAmt(new BigDecimal("100"))
				.create();

		final I_C_Dunning_Candidate candidate = producer.createDunningCandidate(context, sourceDoc);
		Assert.assertNull("No candidate shall be created", candidate);
	}