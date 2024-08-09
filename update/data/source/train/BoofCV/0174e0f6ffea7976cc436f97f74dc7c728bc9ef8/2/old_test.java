@Test
	public void computeNormalization_three() {

		List<AssociatedTriple> list = new ArrayList<>();
		for( int i = 0; i < 12; i++ ) {
			AssociatedTriple p = new AssociatedTriple();

			p.p1.set(rand.nextDouble()*5,rand.nextDouble()*5);
			p.p2.set(rand.nextDouble() * 5, rand.nextDouble() * 5);
			p.p3.set(rand.nextDouble() * 5, rand.nextDouble() * 5);

			list.add(p);
		}

		List<Point2D_F64> list1 = new ArrayList<>();
		List<Point2D_F64> list2 = new ArrayList<>();
		List<Point2D_F64> list3 = new ArrayList<>();

		PerspectiveOps.splitAssociated(list,list1,list2,list3);

		DenseMatrix64F expected1 = new DenseMatrix64F(3,3);
		DenseMatrix64F expected2 = new DenseMatrix64F(3,3);
		DenseMatrix64F expected3 = new DenseMatrix64F(3,3);

		LowLevelMultiViewOps.computeNormalization(list1, expected1);
		LowLevelMultiViewOps.computeNormalization(list2, expected2);
		LowLevelMultiViewOps.computeNormalization(list3, expected3);

		DenseMatrix64F found1 = new DenseMatrix64F(3,3);
		DenseMatrix64F found2 = new DenseMatrix64F(3,3);
		DenseMatrix64F found3 = new DenseMatrix64F(3,3);

		LowLevelMultiViewOps.computeNormalization(list, found1, found2, found3);

		assertTrue(MatrixFeatures_D64.isIdentical(expected1,found1,1e-8));
		assertTrue(MatrixFeatures_D64.isIdentical(expected2,found2,1e-8));
		assertTrue(MatrixFeatures_D64.isIdentical(expected3,found3,1e-8));
	}