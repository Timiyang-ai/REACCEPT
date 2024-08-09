public ConceptProposal saveConceptProposal(ConceptProposal conceptProposal) throws APIException {
		// set the state of the proposal
		if (conceptProposal.getState() == null)
			conceptProposal.setState(OpenmrsConstants.CONCEPT_PROPOSAL_UNMAPPED);
		
		// set the creator and date created
		if (conceptProposal.getCreator() == null && conceptProposal.getEncounter() != null)
			conceptProposal.setCreator(conceptProposal.getEncounter().getCreator());
		else
			conceptProposal.setCreator(Context.getAuthenticatedUser());
		
		if (conceptProposal.getDateCreated() == null && conceptProposal.getEncounter() != null)
			conceptProposal.setDateCreated(conceptProposal.getEncounter().getDateCreated());
		else
			conceptProposal.setDateCreated(new Date());
		return dao.saveConceptProposal(conceptProposal);
	}