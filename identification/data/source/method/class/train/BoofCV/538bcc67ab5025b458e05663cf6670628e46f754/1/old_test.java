@Test
	public void pixelToNormalized() {
		DenseMatrix64F N = new DenseMatrix64F(3,3,true,1,2,3,4,5,6,7,8,9);

		Point2D_F64 a = new Point2D_F64(3,4);
		Point2D_F64 found = new Point2D_F64(3,4);
		Point2D_F64 expected = new Point2D_F64(3,4);

		expected.x = a.x * N.get(0,0) + N.get(0,2);
		expected.y = a.y * N.get(1,1) + N.get(1,2);


		PerspectiveOps.pixelToNormalized(a, found, N);

		assertEquals(found.x,expected.x,1e-8);
		assertEquals(found.y,expected.y,1e-8);
	}