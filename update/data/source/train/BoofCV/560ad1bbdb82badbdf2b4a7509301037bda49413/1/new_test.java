@Test
	public void createMergeList() {
		MergeRegionMeanShiftGray alg = new MergeRegionMeanShiftGray(1);

		ImageSInt32 pixelToRegion = new ImageSInt32(4,4);
		pixelToRegion.data = new int[]
				{0,0,0,1,
				 2,0,0,1,
				 2,0,1,1,
				 0,0,3,1};

		GrowQueue_F32 regionColor = new GrowQueue_F32(3);
		regionColor.add(5);
		regionColor.add(1);
		regionColor.add(6);
		regionColor.add(10);

		alg.createMergeList(pixelToRegion,regionColor);
		int expected[] = new int[]{-1,-1,0,-1};
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);
	}