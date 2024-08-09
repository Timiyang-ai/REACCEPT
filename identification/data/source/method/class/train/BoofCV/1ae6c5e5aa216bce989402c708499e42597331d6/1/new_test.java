@Test
	public void regionPixelId_to_Compact() {
		ImageSInt32 graph = new ImageSInt32(4,5);
		ImageSInt32 output = new ImageSInt32(4,5);

		regionPixelId_to_Compact(graph, output);
		regionPixelId_to_Compact(BoofTesting.createSubImageOf(graph), output);
		regionPixelId_to_Compact(graph, BoofTesting.createSubImageOf(output));
	}