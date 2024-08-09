public static Complex64F[] polynomialRoots( double ...coefficients ) {

		int N = coefficients.length-1;

		// Companion matrix
		DenseMatrix64F c = new DenseMatrix64F(N,N);

		double a = coefficients[N];
		for( int i = 0; i < N; i++ ) {
			c.set(i,N-1,-coefficients[i]/a);
		}
		for( int i = 1; i < N; i++ ) {
			c.set(i,i-1,1);
		}

		// use generalized eigenvalue decomposition to find the roots
		EigenDecomposition<DenseMatrix64F> evd =  DecompositionFactory.eigGeneral(N, false);

		evd.decompose(c);

		Complex64F[] roots = new Complex64F[N];

		for( int i = 0; i < N; i++ ) {
			roots[i] = evd.getEigenvalue(i);
		}

		return roots;
	}