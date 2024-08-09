public void setAsText(String text) throws IllegalArgumentException {
		if (context != null && StringUtils.hasText(text)) {
			ConceptService cs = context.getConceptService();
			ProgramWorkflowService pws = context.getProgramWorkflowService();
			String[] conceptIds = text.split(" ");
			List<Integer> requestConceptIds = new Vector<Integer>();
			for (String id : conceptIds) {
				id = id.trim();
				if (!id.equals("") && !requestConceptIds.contains(Integer.valueOf(id))) //remove whitespace, blank lines, and duplicate entries
					requestConceptIds.add(Integer.valueOf(id));
			}
			
			Collection<ProgramWorkflow> newConceptList = new HashSet<ProgramWorkflow>();
			for (Integer conceptId : requestConceptIds) {
				ProgramWorkflow workflow = pws.getProgramWorkflowByConceptId(conceptId);
				if (workflow == null) {
					workflow = new ProgramWorkflow();
					workflow.setConcept(cs.getConcept(conceptId));
				}
				
				newConceptList.add(workflow);
			}

			setValue(newConceptList);
		} else {
			setValue(null);
		}
	}