@Test(expected = OsmosisRuntimeException.class)
	public final void testProcess9() {
		Relation testRelation;
		
		testRelation = new Relation(3456, 0, new Date(), new OsmUser(12, "OsmosisTest"), 0);
		testRelation.getMembers().add(new RelationMember(1234, EntityType.Node, "role1"));
		testRelation.getTags().add(new Tag("test_key1", "test_value1"));
		
		testOsmWriter.process(new RelationContainer(testRelation));
		testOsmWriter.process(new BoundContainer(new Bound("source")));
	}