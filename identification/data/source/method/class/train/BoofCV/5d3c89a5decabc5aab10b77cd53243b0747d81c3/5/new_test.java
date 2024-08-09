@Test
	public void computeNormalization_two() {

		List<AssociatedPair> list = new ArrayList<>();
		for( int i = 0; i < 12; i++ ) {
			AssociatedPair p = new AssociatedPair();

			p.p2.set(rand.nextDouble()*5,rand.nextDouble()*5);
			p.p1.set(rand.nextDouble() * 5, rand.nextDouble() * 5);

			list.add(p);
		}

		List<Point2D_F64> list1 = new ArrayList<>();
		List<Point2D_F64> list2 = new ArrayList<>();

		PerspectiveOps.splitAssociated(list,list1,list2);

		DMatrixRMaj expected1 = new DMatrixRMaj(3,3);
		DMatrixRMaj expected2 = new DMatrixRMaj(3,3);

		LowLevelMultiViewOps.computeNormalization(list1, expected1);
		LowLevelMultiViewOps.computeNormalization(list2, expected2);

		DMatrixRMaj found1 = new DMatrixRMaj(3,3);
		DMatrixRMaj found2 = new DMatrixRMaj(3,3);

		LowLevelMultiViewOps.computeNormalization(list, found1, found2);

		assertTrue(MatrixFeatures_DDRM.isIdentical(expected1, found1, 1e-8));
		assertTrue(MatrixFeatures_DDRM.isIdentical(expected2,found2,1e-8));
	}