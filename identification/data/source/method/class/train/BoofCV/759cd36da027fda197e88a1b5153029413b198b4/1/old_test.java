@Test
	public void checkMerge_merge() {

		int original[] = new int[]{1,-1,-1,2,2,3,5};

		MergeRegionMeanShift alg = new MergeRegionMeanShift(1,1);
		alg.mergeList.resize(7);
		alg.mergeList.data = original.clone();

		// Both are root nodes
		alg.checkMerge(1,2);
		assertEquals(-1, alg.mergeList.data[1]);
		assertEquals(1,alg.mergeList.data[2]);

		// both are references
		alg.mergeList.data = original.clone();
		alg.checkMerge(0,3);
		int expected[] = new int[]{1,-1,1,1,2,3,5};
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);

		// Both point to the same data but aren't equivalent references, so no change
		alg.mergeList.data = original.clone();
		alg.checkMerge(3,6);
		expected = new int[]{1,-1,-1,2,2,3,2};
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);

		alg.mergeList.data = original.clone();
		alg.checkMerge(6,3);
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);
	}