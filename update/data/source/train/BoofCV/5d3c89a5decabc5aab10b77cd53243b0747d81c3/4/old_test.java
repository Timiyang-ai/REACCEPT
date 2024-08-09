@Test
	public void createTrifocal_SE() {

		TrifocalTensor found = MultiViewOps.createTrifocal(worldToCam2,worldToCam3,null);

		SimpleMatrix R2 = SimpleMatrix.wrap(worldToCam2.getR());
		SimpleMatrix R3 = SimpleMatrix.wrap(worldToCam3.getR());
		SimpleMatrix b4 = new SimpleMatrix(3,1);
		SimpleMatrix a4 = new SimpleMatrix(3,1);

		b4.set(0,worldToCam3.getX());
		b4.set(1,worldToCam3.getY());
		b4.set(2,worldToCam3.getZ());

		a4.set(0,worldToCam2.getX());
		a4.set(1,worldToCam2.getY());
		a4.set(2,worldToCam2.getZ());

		for( int i = 0; i < 3; i++ ) {
			SimpleMatrix ai = R2.extractVector(false, i);
			SimpleMatrix bi = R3.extractVector(false,i);

			SimpleMatrix expected = ai.mult(b4.transpose()).minus(a4.mult(bi.transpose()));

			assertTrue(MatrixFeatures_R64.isIdentical(expected.matrix_F64(),found.getT(i),1e-8));
		}
	}