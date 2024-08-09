@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_CONCEPT_PROPOSALS)
	public ConceptProposal getConceptProposalByUuid(String uuid);