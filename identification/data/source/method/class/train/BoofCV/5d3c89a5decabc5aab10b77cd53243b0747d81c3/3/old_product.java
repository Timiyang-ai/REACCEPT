public static TrifocalTensor createTrifocal( Se3_F64 P2 , Se3_F64 P3 , TrifocalTensor ret ) {
		if( ret == null )
			ret = new TrifocalTensor();

		RowMatrix_F64 R2 = P2.getR();
		RowMatrix_F64 R3 = P3.getR();
		Vector3D_F64 T2 = P2.getT();
		Vector3D_F64 T3 = P3.getT();

		for( int col = 0; col < 3; col++ ) {
			RowMatrix_F64 T = ret.getT(col);

			int index = 0;
			for( int i = 0; i < 3; i++ ) {
				double a_left = R2.unsafe_get(i,col);
				double a_right = T2.getIndex(i);

				for( int j = 0; j < 3; j++ ) {
					T.data[index++] = a_left*T3.getIndex(j) - a_right*R3.unsafe_get(j,col);
				}
			}
		}

		return ret;
	}