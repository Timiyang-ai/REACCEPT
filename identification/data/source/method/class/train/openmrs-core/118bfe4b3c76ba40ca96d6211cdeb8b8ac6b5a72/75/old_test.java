	@Test
	public void getConceptProposalByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "57a68666-5067-11de-80cb-001e378eb67e";
		ConceptProposal conceptProposal = Context.getConceptService().getConceptProposalByUuid(uuid);
		Assert.assertEquals(1, (int) conceptProposal.getConceptProposalId());
	}