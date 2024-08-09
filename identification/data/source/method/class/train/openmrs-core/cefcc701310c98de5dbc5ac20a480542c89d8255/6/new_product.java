@SuppressWarnings("unchecked")
	public void setAsText(String text) throws IllegalArgumentException {
		
		if (StringUtils.hasText(text)) {
			ConceptService cs = Context.getConceptService();
			String[] conceptIds = text.split(" ");
			List<String> requestConceptIds = new Vector<String>();
			//Set<ConceptAnswer> newAnswers = new HashSet<ConceptAnswer>();
			//set up parameter answer Set for easier add/delete functions
			// and removal of duplicates
			for (String id : conceptIds) {
				id = id.trim();
				if (!id.equals("") && !requestConceptIds.contains(id)) //remove whitespace, blank lines, and duplicates
					requestConceptIds.add(id);
			}
			
			Collection<ConceptAnswer> originalConceptAnswers = (Collection<ConceptAnswer>)getValue();
			Collection<ConceptAnswer> deletedConceptAnswers = new HashSet<ConceptAnswer>();
			
			// loop over original concept answers to find any deleted answers
			for (ConceptAnswer origConceptAnswer : originalConceptAnswers) {
				boolean answerDeleted = true;
				for (String conceptId : requestConceptIds) {
					Integer id = getConceptId(conceptId);
					Integer drugId = getDrugId(conceptId);
					Drug answerDrug = origConceptAnswer.getAnswerDrug();
					if (id.equals(origConceptAnswer.getAnswerConcept().getConceptId())) {
						if (drugId == null && answerDrug == null)
							answerDeleted = false;
						else if ((drugId != null && answerDrug != null) &&
							drugId.equals(origConceptAnswer.getAnswerDrug().getDrugId()))
							answerDeleted = false;
					}
				}
				if (answerDeleted)
					deletedConceptAnswers.add(origConceptAnswer);
			}
			
			// loop over those deleted answers to delete them
			for(ConceptAnswer conceptAnswer : deletedConceptAnswers) {
				originalConceptAnswers.remove(conceptAnswer);
			}
			
			// loop over concept ids in the request to add any that are new
			for (String conceptId : requestConceptIds) {
				Integer id = getConceptId(conceptId);
				Integer drugId = getDrugId(conceptId);
				boolean newAnswerConcept = true;
				for (ConceptAnswer origConceptAnswer : originalConceptAnswers) {
					Drug answerDrug = origConceptAnswer.getAnswerDrug();
					if (id.equals(origConceptAnswer.getAnswerConcept().getConceptId())) {
						if (drugId == null && answerDrug == null)
							newAnswerConcept = false;
						else if ((drugId != null && answerDrug != null) &&
							drugId.equals(origConceptAnswer.getAnswerDrug().getDrugId()))
							newAnswerConcept = false;
					}
				}
				// if the current request answer is new, add it to the originals
				if (newAnswerConcept) {
					Concept answer = cs.getConcept(id);
					Drug drug = null;
					if (drugId != null)
						drug = cs.getDrug(drugId);
					ConceptAnswer ac = new ConceptAnswer(answer, drug);
					originalConceptAnswers.add(ac);
				}
			}
			
			log.debug("originalConceptAnswers.getConceptId(): ");
			for (ConceptAnswer a : originalConceptAnswers)
				log.debug("id: " + a.getAnswerConcept().getConceptId());
			
			log.debug("requestConceptIds: ");
			for (String i : requestConceptIds)
				log.debug("id: " + i);
			
			setValue(originalConceptAnswers);
		}
		else {
			setValue(null);
		}
	}