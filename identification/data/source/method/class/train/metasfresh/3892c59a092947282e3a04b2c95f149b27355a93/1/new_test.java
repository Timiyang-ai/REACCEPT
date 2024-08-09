	@Test
	public void onCandidateNewOrChange_dateChange()
	{
		final Candidate demandCandidate = createDemandCandidateWithQuantity("30");

		RepositoryTestHelper.setupMockedRetrieveAvailableToPromise(
				availableToPromiseRepository,
				demandCandidate.getMaterialDescriptor(),
				"0");

		assertThat(demandCandidate.getMaterialDescriptor().getDate()).isEqualTo(NOW); // guard
		demandCandidateHandler.onCandidateNewOrChange(demandCandidate);
	}