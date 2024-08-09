@Test
	public void regionPixelId_to_Compact() {
		GrayS32 graph = new GrayS32(4,5);
		GrayS32 output = new GrayS32(4,5);

		regionPixelId_to_Compact(graph, output);
		regionPixelId_to_Compact(BoofTesting.createSubImageOf(graph), output);
		regionPixelId_to_Compact(graph, BoofTesting.createSubImageOf(output));
	}