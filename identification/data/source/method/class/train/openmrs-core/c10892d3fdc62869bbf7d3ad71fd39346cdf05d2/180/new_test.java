	@Test
	public void saveProgram_shouldCreateProgramWorkflows() {
		
		int numBefore = Context.getProgramWorkflowService().getAllPrograms().size();
		
		Program program = new Program();
		program.setName("TEST PROGRAM");
		program.setDescription("TEST PROGRAM DESCRIPTION");
		program.setConcept(cs.getConcept(3));
		
		ProgramWorkflow workflow = new ProgramWorkflow();
		workflow.setConcept(cs.getConcept(4));
		program.addWorkflow(workflow);
		
		ProgramWorkflowState state1 = new ProgramWorkflowState();
		state1.setConcept(cs.getConcept(5));
		state1.setInitial(true);
		state1.setTerminal(false);
		workflow.addState(state1);
		
		ProgramWorkflowState state2 = new ProgramWorkflowState();
		state2.setConcept(cs.getConcept(6));
		state2.setInitial(false);
		state2.setTerminal(true);
		workflow.addState(state2);
		
		Context.getProgramWorkflowService().saveProgram(program);
		
		assertEquals("Failed to create program", numBefore + 1, Context.getProgramWorkflowService().getAllPrograms().size());
		Program p = Context.getProgramWorkflowService().getProgramByName("TEST PROGRAM");
		assertNotNull("Program is null", p);
		assertNotNull("Workflows is null", p.getWorkflows());
		assertEquals("Wrong number of workflows", 1, p.getWorkflows().size());
		
		ProgramWorkflow wf = p.getWorkflowByName("CIVIL STATUS");
		assertNotNull(wf);
		
		List<String> names = new ArrayList<>();
		for (ProgramWorkflowState s : wf.getStates()) {
			names.add(s.getConcept().getName().getName());
		}
		TestUtil.assertCollectionContentsEquals(Arrays.asList("SINGLE", "MARRIED"), names);
	}