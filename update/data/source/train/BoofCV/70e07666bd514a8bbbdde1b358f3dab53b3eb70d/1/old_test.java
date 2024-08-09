@Test
	public void computeNormalization_two() {

		List<AssociatedPair> list = new ArrayList<AssociatedPair>();
		for( int i = 0; i < 12; i++ ) {
			AssociatedPair p = new AssociatedPair();

			p.currLoc.set(rand.nextDouble()*5,rand.nextDouble()*5);
			p.keyLoc.set(rand.nextDouble() * 5, rand.nextDouble() * 5);

			list.add(p);
		}

		List<Point2D_F64> list1 = new ArrayList<Point2D_F64>();
		List<Point2D_F64> list2 = new ArrayList<Point2D_F64>();

		PerspectiveOps.splitAssociated(list,list1,list2);

		DenseMatrix64F expected1 = new DenseMatrix64F(3,3);
		DenseMatrix64F expected2 = new DenseMatrix64F(3,3);

		LowLevelMultiViewOps.computeNormalization(list1, expected1);
		LowLevelMultiViewOps.computeNormalization(list2, expected2);

		DenseMatrix64F found1 = new DenseMatrix64F(3,3);
		DenseMatrix64F found2 = new DenseMatrix64F(3,3);

		LowLevelMultiViewOps.computeNormalization(list, found1, found2);

		assertTrue(MatrixFeatures.isIdentical(expected1, found1, 1e-8));
		assertTrue(MatrixFeatures.isIdentical(expected2,found2,1e-8));
	}