@Test
	public void regionPixelId_to_Compact() {
		ImageSInt32 graph = new ImageSInt32(4,5);
		ImageSInt32 output = new ImageSInt32(4,5);

		graph.data = new int[]{
				2, 2, 2, 5,
				5, 5, 5, 5,
				2, 2, 2, 2,
				15,15,15,15,
				15,15,15,15};


		GrowQueue_I32 rootNodes = new GrowQueue_I32();
		rootNodes.add(2);
		rootNodes.add(5);
		rootNodes.add(15);


		ImageSegmentationOps.regionPixelId_to_Compact(graph, rootNodes, output);


		ImageSInt32 expected = new ImageSInt32(4,5);
		expected.data = new int[]{
				0, 0, 0, 1,
				1, 1, 1, 1,
				0, 0, 0, 0,
				2, 2, 2, 2,
				2, 2, 2, 2};

		BoofTesting.assertEquals(expected, output, 1e-4);
	}