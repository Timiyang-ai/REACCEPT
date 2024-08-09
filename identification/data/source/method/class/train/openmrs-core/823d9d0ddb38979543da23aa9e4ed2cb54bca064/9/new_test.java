	@Test
	public void saveConceptProposal_shouldReturnSavedConceptProposalObject() {
		final String ORIGINAL_TEXT = "OriginalText";
		ConceptProposal conceptProposal = new ConceptProposal();
		conceptProposal.setOriginalText(ORIGINAL_TEXT);
		List<ConceptProposal> existingConceptProposals = conceptService.getConceptProposals(ORIGINAL_TEXT);
		assertTrue(existingConceptProposals.isEmpty());
		ConceptProposal savedConceptProposal = conceptService.saveConceptProposal(conceptProposal);
		assertEquals(ORIGINAL_TEXT, savedConceptProposal.getOriginalText());
		assertEquals(conceptProposal, savedConceptProposal);
	}