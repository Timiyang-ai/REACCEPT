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

		NormalizationPoint2D expected1 = new NormalizationPoint2D();
		NormalizationPoint2D expected2 = new NormalizationPoint2D();

		LowLevelMultiViewOps.computeNormalization(list1, expected1);
		LowLevelMultiViewOps.computeNormalization(list2, expected2);

		NormalizationPoint2D found1 = new NormalizationPoint2D();
		NormalizationPoint2D found2 = new NormalizationPoint2D();

		LowLevelMultiViewOps.computeNormalization(list, found1, found2);

		assertTrue(expected1.isEquals(found1,1e-8));
		assertTrue(expected2.isEquals(found2,1e-8));
	}