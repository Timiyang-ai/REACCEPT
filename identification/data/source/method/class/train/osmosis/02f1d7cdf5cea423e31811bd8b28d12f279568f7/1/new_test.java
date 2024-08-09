@Test(expected=OsmosisRuntimeException.class)
	public final void testProcess9() {
		Relation testRelation;
		testRelation = new Relation(3456, new Date(), new OsmUser("OsmosisTest", 12), 0);
		testRelation.addMember(new RelationMember(1234, EntityType.Node, "role1"));
		testRelation.addTag(new Tag("test_key1", "test_value1"));
		testOsmWriter.process(new RelationContainer(testRelation));
		testOsmWriter.process(new BoundContainer(new Bound("source")));
	}