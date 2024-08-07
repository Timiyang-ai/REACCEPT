@Test
    public void testGetTypeMap() {
	Result result = new Result(false, false, 1000, 2000, new BranchTrace(), vector, "unittest.id");
	rule.onApply(result);
	vector.addModification(new AddMessageModification(new ServerHelloDoneMessage(), new SendAction()));
	rule.onApply(result);
	List<ModificationCounter> counterList= rule.getCounterList();
	ModificationCounter counter = counterList.get(1);
	assertTrue(counter.getCounter() == 2);
	counter = counterList.get(0);
	assertTrue(counter.getCounter() == 3);
    }