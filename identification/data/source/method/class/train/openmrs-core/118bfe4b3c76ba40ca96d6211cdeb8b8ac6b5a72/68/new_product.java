@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public ConceptProposal getConceptProposalByUuid(String uuid);