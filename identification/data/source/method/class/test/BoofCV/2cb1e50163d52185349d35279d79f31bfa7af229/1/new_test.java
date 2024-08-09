@Test
	public void markMerge_quick() {

		int expected[] = new int[]{1,1,2,2,2,3,5};

		RegionMergeTree alg = new RegionMergeTree();
		alg.mergeList.resize(7);
		alg.mergeList.data = new int[]{1,1,2,2,2,3,5};

		// case 1 - Both are references
		alg.markMerge(3,4);
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);

		// case 2 - A is a reference and B is not
		alg.markMerge(3, 2);
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);

		// case 3 - B is a reference and A is not
		alg.markMerge(2, 3);
		for( int i = 0; i < expected.length; i++ )
			assertEquals(expected[i],alg.mergeList.data[i]);
	}