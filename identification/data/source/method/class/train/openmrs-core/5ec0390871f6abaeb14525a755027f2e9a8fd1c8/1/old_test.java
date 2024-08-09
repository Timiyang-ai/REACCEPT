	@Test
	public void setAsText_shouldUpdateWorkflowsInProgram() {
		Program program = Context.getProgramWorkflowService().getProgram(1);
		WorkflowCollectionEditor editor = new WorkflowCollectionEditor();
		
		Assert.assertEquals(2, program.getWorkflows().size());
		
		editor.setAsText("1:3");
		
		Assert.assertEquals(1, program.getWorkflows().size());
		Assert.assertEquals(3, program.getWorkflows().iterator().next().getConcept().getConceptId().intValue());
		Assert.assertEquals(3, program.getAllWorkflows().size());
	}