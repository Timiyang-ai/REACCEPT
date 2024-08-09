@Test
	public final void testProcess8() {
		Relation testRelation;
		testRelation = new Relation(3456, new Date(), "OsmosisTest");
		testRelation.addMember(new RelationMember(1234, EntityType.Node, "role1"));
		testRelation.addTag(new Tag("test_key1", "test_value1"));
		testOsmWriter.process(new RelationContainer(testRelation));
		// Nothing to assert; just expect no exception
	}