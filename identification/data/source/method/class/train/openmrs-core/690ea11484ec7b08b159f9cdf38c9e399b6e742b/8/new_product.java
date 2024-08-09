public Concept mapConceptProposalToConcept(ConceptProposal cp, Concept mappedConcept) throws APIException {
		
		if (cp.getState().equals(OpenmrsConstants.CONCEPT_PROPOSAL_REJECT)) {
			cp.rejectConceptProposal();
			saveConceptProposal(cp);
			return null;
		}
		
		if (mappedConcept == null)
			throw new APIException("Illegal Mapped Concept");
		
		if (cp.getState().equals(OpenmrsConstants.CONCEPT_PROPOSAL_CONCEPT) || !StringUtils.hasText(cp.getFinalText())) {
			cp.setState(OpenmrsConstants.CONCEPT_PROPOSAL_CONCEPT);
			cp.setFinalText("");
		} else if (cp.getState().equals(OpenmrsConstants.CONCEPT_PROPOSAL_SYNONYM)) {
			
			checkIfLocked();
			
			String finalText = cp.getFinalText();
			ConceptName conceptName = new ConceptName(finalText, null);
			conceptName.setConcept(mappedConcept);
			
			conceptName.setDateCreated(new Date());
			conceptName.setCreator(Context.getAuthenticatedUser());
			//If this is pre 1.9
			if(conceptName.getUuid() == null)
				conceptName.setUuid(UUID.randomUUID().toString());
			mappedConcept.addName(conceptName);
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
			if(ob.getUuid() == null)
				ob.setUuid(UUID.randomUUID().toString());
			cp.setObs(ob);
		}
		
		return mappedConcept;
	}