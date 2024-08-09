@Test
	public final void testProcess3() {
		testOsmWriter.process(
				new NodeContainer(
						new Node(
								1234, 0, new Date(), new OsmUser(12, "OsmosisTest"), 0, new ArrayList<Tag>(), 20, 20)));
		// Nothing to assert; just expect no exception
	}