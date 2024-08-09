@Test
	public void testAssignToNode(){
		//[id=2, name=分行经理审批]
		getJBPMApplication().assignToNode(1l, 2l);
		testQueryTodoList();
	}