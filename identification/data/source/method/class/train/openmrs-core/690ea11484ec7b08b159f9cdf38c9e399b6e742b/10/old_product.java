public Concept mapConceptProposalToConcept(ConceptProposal cp,
	        Concept mappedConcept) throws APIException {

		if (cp.getState().equals(OpenmrsConstants.CONCEPT_PROPOSAL_REJECT)) {
			cp.rejectConceptProposal();
			saveConceptProposal(cp);
		}

		if (mappedConcept == null)
			throw new APIException("Illegal Mapped Concept");

		if (cp.getState().equals(OpenmrsConstants.CONCEPT_PROPOSAL_CONCEPT)
		        || !StringUtils.hasText(cp.getFinalText())) {
			cp.setState(OpenmrsConstants.CONCEPT_PROPOSAL_CONCEPT);
			cp.setFinalText("");
		} else if (cp.getState()
		             .equals(OpenmrsConstants.CONCEPT_PROPOSAL_SYNONYM)) {

			checkIfLocked();

			String finalText = cp.getFinalText();
			ConceptSynonym syn = new ConceptSynonym(mappedConcept,
			                                        finalText,
			                                        Context.getLocale());
			syn.setDateCreated(new Date());
			syn.setCreator(Context.getAuthenticatedUser());

			mappedConcept.addSynonym(syn);
			mappedConcept.setChangedBy(Context.getAuthenticatedUser());
			mappedConcept.setDateChanged(new Date());
			updateConceptWord(mappedConcept);
		}

		cp.setMappedConcept(mappedConcept);

		if (cp.getObsConcept() != null) {
			Obs ob = new Obs();
			ob.setEncounter(cp.getEncounter());
			ob.setConcept(cp.getObsConcept());
			ob.setValueCoded(cp.getMappedConcept());
			ob.setCreator(Context.getAuthenticatedUser());
			ob.setDateCreated(new Date());
			ob.setObsDatetime(cp.getEncounter().getEncounterDatetime());
			ob.setLocation(cp.getEncounter().getLocation());
			ob.setPerson(cp.getEncounter().getPatient());
			cp.setObs(ob);
		}
		
		return mappedConcept;
	}