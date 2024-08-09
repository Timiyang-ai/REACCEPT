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
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Program not found: " + text, ex);
			}
		} else {
			setValue(null);
		}
	}