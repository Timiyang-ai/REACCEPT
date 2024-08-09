@Test
	public void testGetNeighborsND() {
		//This setup isn't relevant to this test
		defaultSetup();
		parameters.setInputDimensions(new int[] { 9, 5 });
		parameters.setColumnDimensions(new int[] { 5, 5 });
		initSP();
		
		////////////////////// Test not part of Python port /////////////////////
		int[] result = SpatialPooler.getNeighborsND(mem, 2, 3, true).toArray();
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
		int[] dimensions = new int[] { 5, 7, 2 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		int radius = 1;
		int x = 1;
		int y = 3;
		int z = 2;
		int columnIndex = mem.getInputMatrix().computeIndex(new int[] { z, y, x });
		int[] neighbors = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		String expect = "[18, 19, 20, 21, 22, 23, 32, 33, 34, 36, 37, 46, 47, 48, 49, 50, 51]";
		assertEquals(expect, ArrayUtils.print1DArray(neighbors));
		
		/////////////////////////////////////////
		
		defaultSetup();
		dimensions = new int[] { 5, 7, 9 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		radius = 3;
		x = 0;
		y = 0;
		z = 3;
		columnIndex = mem.getInputMatrix().computeIndex(new int[] { z, y, x });
		neighbors = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		expect = "[0, 1, 2, 3, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 18, 19, 20, 21, 24, 25, 26, "
				+ "27, 28, 29, 30, 33, 34, 35, 36, 37, 38, 39, 42, 43, 44, 45, 46, 47, 48, 51, "
				+ "52, 53, 54, 55, 56, 57, 60, 61, 62, 63, 64, 65, 66, 69, 70, 71, 72, 73, 74, "
				+ "75, 78, 79, 80, 81, 82, 83, 84, 87, 88, 89, 90, 91, 92, 93, 96, 97, 98, 99, "
				+ "100, 101, 102, 105, 106, 107, 108, 109, 110, 111, 114, 115, 116, 117, 118, 119, "
				+ "120, 123, 124, 125, 126, 127, 128, 129, 132, 133, 134, 135, 136, 137, 138, 141, "
				+ "142, 143, 144, 145, 146, 147, 150, 151, 152, 153, 154, 155, 156, 159, 160, 161, "
				+ "162, 163, 164, 165, 168, 169, 170, 171, 172, 173, 174, 177, 178, 179, 180, 181, "
				+ "182, 183, 186, 187, 188, 190, 191, 192, 195, 196, 197, 198, 199, 200, 201, 204, "
				+ "205, 206, 207, 208, 209, 210, 213, 214, 215, 216, 217, 218, 219, 222, 223, 224, "
				+ "225, 226, 227, 228, 231, 232, 233, 234, 235, 236, 237, 240, 241, 242, 243, 244, "
				+ "245, 246, 249, 250, 251, 252, 253, 254, 255, 258, 259, 260, 261, 262, 263, 264, "
				+ "267, 268, 269, 270, 271, 272, 273, 276, 277, 278, 279, 280, 281, 282, 285, 286, "
				+ "287, 288, 289, 290, 291, 294, 295, 296, 297, 298, 299, 300, 303, 304, 305, 306, "
				+ "307, 308, 309, 312, 313, 314]";
		assertEquals(expect, ArrayUtils.print1DArray(neighbors));
		
		/////////////////////////////////////////
		
		defaultSetup();
		dimensions = new int[] { 5, 10, 7, 6 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		radius = 4;
		int w = 2;
		x = 5;
		y = 6;
		z = 2;
		columnIndex = mem.getInputMatrix().computeIndex(new int[] { z, y, x, w });
		neighbors = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		
		TIntHashSet trueNeighbors = new TIntHashSet();
		for(int i = -radius;i <= radius;i++) {
			for(int j = -radius;j <= radius;j++) {
				for(int k = -radius;k <= radius;k++) {
					for(int m = -radius;m <= radius;m++) {
						int zprime = (int)ArrayUtils.positiveRemainder((z + i), dimensions[0]);
						int yprime = (int)ArrayUtils.positiveRemainder((y + j), dimensions[1]);
						int xprime = (int)ArrayUtils.positiveRemainder((x + k), dimensions[2]);
						int wprime = (int)ArrayUtils.positiveRemainder((w + m), dimensions[3]);
						trueNeighbors.add(mem.getInputMatrix().computeIndex(new int[] { zprime, yprime, xprime, wprime }));
					}
				}
			}
		}
		trueNeighbors.remove(columnIndex);
		int[] tneighbors = ArrayUtils.unique(trueNeighbors.toArray());
		assertEquals(ArrayUtils.print1DArray(tneighbors), ArrayUtils.print1DArray(neighbors));
		
		/////////////////////////////////////////
		//Tests from getNeighbors1D from Python unit test
		defaultSetup();
		dimensions = new int[] { 8 };
		parameters.setInputDimensions(dimensions);
		initSP();
		SparseBinaryMatrix sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		sbm.set(new int[] { 2, 4 }, new int[] { 1, 1 });
		radius = 1;
		columnIndex = 3;
		int[] mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		TIntArrayList msk = new TIntArrayList(mask);
		TIntArrayList neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		//////
		defaultSetup();
		dimensions = new int[] { 8 };
		parameters.setInputDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		sbm.set(new int[] { 1, 2, 4, 5 }, new int[] { 1, 1, 1, 1 });
		radius = 2;
		columnIndex = 3;
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		//Wrap around
		defaultSetup();
		dimensions = new int[] { 8 };
		parameters.setInputDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		sbm.set(new int[] { 1, 2, 6, 7 }, new int[] { 1, 1, 1, 1 });
		radius = 2;
		columnIndex = 0;
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		//Radius too big
		defaultSetup();
		dimensions = new int[] { 8 };
		parameters.setInputDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		sbm.set(new int[] { 0, 1, 2, 3, 4, 5, 7 }, new int[] { 1, 1, 1, 1, 1, 1, 1 });
		radius = 20;
		columnIndex = 6;
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		//These are all the same tests from 2D
		defaultSetup();
		dimensions = new int[] { 6, 5 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		int[][] input = new int[][] { {0, 0, 0, 0, 0},
		                          {0, 0, 0, 0, 0},
		                          {0, 1, 1, 1, 0},
		                          {0, 1, 0, 1, 0},
		                          {0, 1, 1, 1, 0},
		                          {0, 0, 0, 0, 0}};
		for(int i = 0;i < input.length;i++) {
			for(int j = 0;j < input[i].length;j++) {
				if(input[i][j] == 1) 
					sbm.set(sbm.computeIndex(new int[] { i, j }), 1);
			}
		}
		radius = 1;
		columnIndex = 3*5 + 2;
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		////////
		defaultSetup();
		dimensions = new int[] { 6, 5 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		input = new int[][] { {0, 0, 0, 0, 0},
						      {1, 1, 1, 1, 1},
						      {1, 1, 1, 1, 1},
						      {1, 1, 0, 1, 1},
						      {1, 1, 1, 1, 1},
						      {1, 1, 1, 1, 1}};
		for(int i = 0;i < input.length;i++) {
			for(int j = 0;j < input[i].length;j++) {
				if(input[i][j] == 1) 
					sbm.set(sbm.computeIndex(new int[] { i, j }), 1);
			}
		}
		radius = 2;
		columnIndex = 3*5 + 2;
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		//Radius too big
		defaultSetup();
		dimensions = new int[] { 6, 5 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		input = new int[][] { {1, 1, 1, 1, 1},
						      {1, 1, 1, 1, 1},
						      {1, 1, 1, 1, 1},
						      {1, 1, 0, 1, 1},
						      {1, 1, 1, 1, 1},
						      {1, 1, 1, 1, 1}};
		for(int i = 0;i < input.length;i++) {
			for(int j = 0;j < input[i].length;j++) {
				if(input[i][j] == 1) 
					sbm.set(sbm.computeIndex(new int[] { i, j }), 1);
			}
		}
		radius = 7;
		columnIndex = 3*5 + 2;
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
		
		//Wrap-around
		defaultSetup();
		dimensions = new int[] { 6, 5 };
		parameters.setInputDimensions(dimensions);
		parameters.setColumnDimensions(dimensions);
		initSP();
		sbm = (SparseBinaryMatrix)mem.getInputMatrix();
		input = new int[][] { {1, 0, 0, 1, 1},
						      {0, 0, 0, 0, 0},
						      {0, 0, 0, 0, 0},
						      {0, 0, 0, 0, 0},
						      {1, 0, 0, 1, 1},
						      {1, 0, 0, 1, 0}};
		for(int i = 0;i < input.length;i++) {
			for(int j = 0;j < input[i].length;j++) {
				if(input[i][j] == 1) 
					sbm.set(sbm.computeIndex(new int[] { i, j }), 1);
			}
		}
		radius = 1;
		columnIndex = sbm.getMaxIndex();
		mask = SpatialPooler.getNeighborsND(mem, columnIndex, radius, true).toArray();
		msk = new TIntArrayList(mask);
		neg = new TIntArrayList(ArrayUtils.range(0, dimensions[0]));
		neg.removeAll(msk);
		assertTrue(sbm.all(mask));
		assertFalse(sbm.any(neg));
	}