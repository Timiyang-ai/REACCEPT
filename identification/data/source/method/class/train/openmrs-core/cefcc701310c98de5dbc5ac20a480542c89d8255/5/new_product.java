public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			ConceptService cs = Context.getConceptService();
			String[] conceptIds = text.split(" ");
			List<String> requestConceptIds = new Vector<String>();
			//set up parameter answer Set for easier add/delete functions and removal of duplicates
			for (String id : conceptIds) {
				id = id.trim();
				if (!id.equals("") && !requestConceptIds.contains(id)) //remove whitespace, blank lines, and duplicates
					requestConceptIds.add(id);
			}
			
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
						else if ((drugId != null && answerDrug != null)
						        && drugId.equals(origConceptAnswer.getAnswerDrug().getDrugId()))
							answerDeleted = false;
					}
				}
				if (answerDeleted)
					deletedConceptAnswers.add(origConceptAnswer);
			}
			
			// loop over those deleted answers to delete them
			for (ConceptAnswer conceptAnswer : deletedConceptAnswers) {
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
						else if ((drugId != null && answerDrug != null) && drugId.equals(answerDrug.getDrugId()))
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
			
			//loop over to set the order
			//as the list comes into 'requestConceptIds' in the order the user wants
			//  there are 2 conditions that will require the sort_weights to be reassigned
			//    1) any ConceptAnswer.sortWeight == NULL (meaning it is just added)
			//    2) the list is not in ASCENDING order (example sort order of the list is 1, 2, 10, 9)
			//  -startIdx (start index) is where in this list we will start to reassign the sort_weights
			Double lastWeightSeen = null;
			int startIdx = -1;//the idx to start at, if we have a NULL sort weight (new concept answer) or sort weights are not ascending
			for (int i = 0; i < requestConceptIds.size() - 1; i++) {
				Integer id1 = getConceptId(requestConceptIds.get(i));
				ConceptAnswer ca1 = getConceptAnswerFromOriginal(id1);
				
				if (ca1.getSortWeight() == null) {
					if (lastWeightSeen == null) {
						//start at 1, we're at the beginning
						lastWeightSeen = 1d;
					} else {
						//we start at +1
						lastWeightSeen += 1;
					}
					startIdx = i;
					break;
				}
				
				Integer id2 = getConceptId(requestConceptIds.get(i + 1));
				ConceptAnswer ca2 = getConceptAnswerFromOriginal(id2);
				int c = ca1.compareTo(ca2);
				if (c > 0) {
					startIdx = i;
					lastWeightSeen = ca1.getSortWeight();
					break;
				}
				
				lastWeightSeen = ca1.getSortWeight();
			}
			
			if (startIdx != -1) {
				//then we need to re-weight
				for (int i = startIdx; i < requestConceptIds.size(); i++) {
					Integer id = getConceptId(requestConceptIds.get(i));
					ConceptAnswer ca = getConceptAnswerFromOriginal(id);
					ca.setSortWeight(lastWeightSeen++);
				}
			}
			
			log.debug("originalConceptAnswers.getConceptId(): ");
			for (ConceptAnswer a : originalConceptAnswers)
				log.debug("id: " + a.getAnswerConcept().getConceptId());
			
			log.debug("requestConceptIds: ");
			for (String i : requestConceptIds)
				log.debug("id: " + i);
		} else {
			originalConceptAnswers.clear();
		}
		
		setValue(originalConceptAnswers);
	}