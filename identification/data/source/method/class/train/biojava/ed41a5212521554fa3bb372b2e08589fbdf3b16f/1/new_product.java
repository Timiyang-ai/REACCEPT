public static Quat4d relativeOrientation(Point3d[] fixed, Point3d[] moved) {
		Matrix m = CalcPoint.formMatrix(moved, fixed); // inverse
		EigenvalueDecomposition eig = m.eig();
		double[][] v = eig.getV().getArray();
		Quat4d q = new Quat4d(v[1][3], v[2][3], v[3][3], v[0][3]);
		q.normalize();
		q.conjugate();
		return q;
	}