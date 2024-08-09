@Test
	public void testGetNeighborsND() {
		defaultSetup();
		
		initSP();
		
		int[] result = sp.getNeighborsND(new SparseBinaryMatrix<Column>(new int[] { 9, 5 }), 2, 3, true);
		int[] expected = new int[] { 
			0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 
			13, 14, 15, 16, 17, 18, 19, 30, 31, 32, 33, 
			34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 
		};
		for(int i = 0;i < result.length;i++) {
			assertEquals(expected[i], result[i]);
		}
		System.out.println(ArrayUtils.print1DArray(result));
	}