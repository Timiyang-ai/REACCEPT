@Test
    public void testGetTypeMap() {
	Result result = new Result(false, false, 1000, 2000, new BranchTrace(), vector, "unittest.id");
	rule.onApply(result);
	vector.addModification(new AddMessageModification(new ServerHelloDoneMessage(), new SendAction()));
	rule.onApply(result);
	HashMap<ModificationType, MutableInt> typeMap = rule.getTypeMap();
	MutableInt val = typeMap.get(ModificationType.ADD_RECORD);
	assertTrue(val.getValue() == 2);
	val = typeMap.get(ModificationType.ADD_MESSAGE);
	assertTrue(val.getValue() == 3);
    }