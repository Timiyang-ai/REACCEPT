	@Test
	public void mapConceptProposalToConcept_shouldNotRequireMappedConceptOnRejectAction() {
		String uuid = "af4ae460-0e2b-11e0-a94b-469c3c5a0c2f";
		ConceptProposal proposal = Context.getConceptService().getConceptProposalByUuid(uuid);
		Assert.assertNotNull("could not find proposal " + uuid, proposal);
		proposal.setState(OpenmrsConstants.CONCEPT_PROPOSAL_REJECT);
		try {
			Context.getConceptService().mapConceptProposalToConcept(proposal, null);
		}
		catch (APIException ex) {
			Assert.fail("cought APIException when rejecting a proposal with null mapped concept");
		}
	}