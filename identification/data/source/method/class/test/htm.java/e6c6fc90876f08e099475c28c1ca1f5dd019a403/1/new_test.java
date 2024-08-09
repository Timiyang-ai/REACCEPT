@Test
	public void testGetNeighborsND() {
		//This setup isn't relevant to this test
		defaultSetup();
		parameters.setInputDimensions(new int[] { 9, 5 });
		initSP();
		
		////////////////////// Test not part of Python port /////////////////////
		int[] result = sp.getNeighborsND(new SparseBinaryMatrix(new int[] { 9, 5 }), 2, 3, true);
		int[] expected = new int[] { 
			0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 
			13, 14, 15, 16, 17, 18, 19, 30, 31, 32, 33, 
			34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 
		};
		for(int i = 0;i < result.length;i++) {
			assertEquals(expected[i], result[i]);
		}
		/////////////////////////////////////////////////////////////////////////
		
		defaultSetup();
		parameters.setInputDimensions(new int[] { 5, 7, 2 });
		initSP();
		
		int[] dimensions = new int[] { 5, 7, 2 };
		SparseBinaryMatrix layout = new SparseBinaryMatrix(dimensions);
			
		int radius = 1;
		int x = 1;
		int y = 3;
		int z = 2;
		int columnIndex = layout.computeIndex(new int[] { z, y, x });
		int[] neighbors = sp.getNeighborsND(layout, columnIndex, radius, true);
		String expect = "[18, 19, 20, 21, 22, 23, 32, 33, 34, 36, 37, 46, 47, 48, 49, 50, 51]";
		assertEquals(expect, ArrayUtils.print1DArray(neighbors));
	}