	@Test
	void createNext()
	{
		final Instant time = SystemTime.asInstant();
		final PageDescriptor pd = PageDescriptor.createNew("querySelectionUUID", 10, 100, time);
		assertThat(pd.getOffset()).isEqualTo(0);

		final PageDescriptor nextPd = pd.createNext();
		assertThat(nextPd.getOffset()).isEqualTo(10);

		assertThat(nextPd.getPageIdentifier()).isNotEqualTo(pd.getPageIdentifier());
		assertThat(nextPd.getPageIdentifier().getSelectionUid()).isEqualTo(pd.getPageIdentifier().getSelectionUid());

		assertThat(nextPd.getTotalSize()).isEqualTo(pd.getTotalSize());
		assertThat(nextPd.getPageSize()).isEqualTo(pd.getPageSize());
		assertThat(nextPd.getSelectionTime()).isEqualTo(time);
	}