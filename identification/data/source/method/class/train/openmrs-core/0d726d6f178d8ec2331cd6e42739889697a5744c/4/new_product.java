public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.startsWith("concept.")) {
					Integer conceptId = Integer.valueOf(text.substring(text.indexOf('.') + 1));
					Concept c = Context.getConceptService().getConcept(conceptId);
					setValue(Context.getProgramWorkflowService().getProgramByName(c.getName().getName()));
				} else {
					Integer programId = Integer.valueOf(text);
					setValue(Context.getProgramWorkflowService().getProgram(programId));
				}
			}
			catch (Exception ex) {
				Program p;
				if (text.startsWith("concept.")) {
					Concept c = Context.getConceptService().getConceptByUuid(text.substring(text.indexOf('.') + 1));
					p = Context.getProgramWorkflowService().getProgramByName(c.getName().getName());
				} else {
					p = Context.getProgramWorkflowService().getProgramByUuid(text);
				}
				
				setValue(p);
				if (p == null) {
					log.error("Error setting text: " + text, ex);
					throw new IllegalArgumentException("Program not found: " + text, ex);
				}
			}
		} else {
			setValue(null);
		}
	}