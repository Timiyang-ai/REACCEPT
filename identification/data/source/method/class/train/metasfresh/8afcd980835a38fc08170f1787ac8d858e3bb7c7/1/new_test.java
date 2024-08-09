	@Test
	public void stripZerosAndLogIssueIfBigDecimalScaleTooBig_null()
	{
		final Object result = POUtils.stripZerosAndLogIssueIfBigDecimalScaleTooBig(null, po);
		assertThat(result).isNull();
		assertThat(POJOLookupMap.get().getRecords(I_AD_Issue.class)).isEmpty();
	}