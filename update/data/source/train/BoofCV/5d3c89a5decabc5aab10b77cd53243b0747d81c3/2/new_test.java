@Test
	public void setupA1_setupA2() {
		double x = 0.5, y = 2, z = -0.2, w = 1;

		SimpleMatrix E = SimpleMatrix.wrap(constructE(x,y,z,w));

		DMatrixRMaj A = new DMatrixRMaj(10,10);
		DMatrixRMaj B = new DMatrixRMaj(10,10);

		HelperNister5 alg = new HelperNister5();
		alg.setNullSpace(X,Y,Z,W);
		alg.setupA1(A);
		alg.setupA2(B);

		DMatrixRMaj Y1 = new DMatrixRMaj(10,1);
		DMatrixRMaj Y2 = new DMatrixRMaj(10,1);

		CommonOps_DDRM.mult(A,createCoefsA(x,y,z),Y1);
		CommonOps_DDRM.mult(B,createCoefsB(x, y, z),Y2);

		DMatrixRMaj Y = new DMatrixRMaj(10,1);

		CommonOps_DDRM.add(Y1,Y2,Y);

		// compute the constraints equations
		SimpleMatrix EEt = E.mult(E.transpose());
		SimpleMatrix EEtE = EEt.mult(E);
		SimpleMatrix aE = E.scale(-0.5*EEt.trace());
		DMatrixRMaj eq2 = EEtE.plus(aE).matrix_F64();

		// check the solution
		assertEquals(E.determinant(),Y.data[0],1e-8);
		assertEquals(eq2.data[0],Y.data[1],1e-8);
		assertEquals(eq2.data[1],Y.data[2],1e-8);
		assertEquals(eq2.data[2],Y.data[3],1e-8);
		assertEquals(eq2.data[3],Y.data[4],1e-8);
		assertEquals(eq2.data[4],Y.data[5],1e-8);
		assertEquals(eq2.data[5],Y.data[6],1e-8);
		assertEquals(eq2.data[6],Y.data[7],1e-8);
		assertEquals(eq2.data[7],Y.data[8],1e-8);
		assertEquals(eq2.data[8],Y.data[9],1e-8);
	}