@Test(expected=OsmosisRuntimeException.class)
	public final void testProcess7() {
		Way testWay;
		testWay = new Way(3456, new Date(), TEST_USER);
		testWay.addWayNode(new WayNode(1234));
		testWay.addWayNode(new WayNode(1235));
		testWay.addTag(new Tag("test_key1", "test_value1"));
		testOsmWriter.process(new WayContainer(testWay));
		testOsmWriter.process(new BoundContainer(new Bound("source")));
	}