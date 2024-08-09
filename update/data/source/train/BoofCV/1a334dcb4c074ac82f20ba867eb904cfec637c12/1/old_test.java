@Test
	public void polynomialRoots() {
		Complex64F[] roots = PolynomialSolver.polynomialRoots(4, 3, 2, 1);

		int numReal = 0;
		for( Complex64F c : roots ) {
			if( c.isReal() ) {
				assertEquals(0,cubic(4, 3, 2, 1,c.real),1e-8);
				numReal++;
			}
		}

		assertTrue(numReal>0);
	}