public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			ConceptService cs = Context.getConceptService();
			ProgramWorkflowService pws = Context.getProgramWorkflowService();
			try {
				int ind = text.indexOf(":");
				String progIdStr = text.substring(0, ind);
				text = text.substring(ind + 1);
				if (program == null)
					// if a program wasn't passed in, try to look it up now
					program = pws.getProgram(Integer.valueOf(progIdStr));
			}
			catch (Exception ex) {}
			
			String[] conceptIds = text.split(" ");
			Set<ProgramWorkflow> oldSet = program == null ? new HashSet<ProgramWorkflow>() : program.getAllWorkflows();
			Set<Integer> newConceptIds = new HashSet<Integer>();
			
			for (String id : conceptIds) {
				if (id.trim().length() == 0)
					continue;
				log.debug("trying " + id);
				newConceptIds.add(Integer.valueOf(id.trim()));
			}
			
			// go through oldSet and see what we need to keep and what we need to unvoid
			Set<Integer> alreadyDone = new HashSet<Integer>();
			for (ProgramWorkflow pw : oldSet) {
				if (!newConceptIds.contains(pw.getConcept().getConceptId())) {
					pw.setRetired(true);
				} else if (pw.isRetired()) { // && newConceptIds.contains(pw...)
					pw.setRetired(false);
				}
				alreadyDone.add(pw.getConcept().getConceptId());
			}
			
			// now add any new ones
			newConceptIds.removeAll(alreadyDone);
			for (Integer conceptId : newConceptIds) {
				ProgramWorkflow pw = new ProgramWorkflow();
				pw.setProgram(program);
				pw.setConcept(cs.getConcept(conceptId));
				oldSet.add(pw);
			}
			
			setValue(oldSet);
		} else {
			setValue(null);
		}
	}