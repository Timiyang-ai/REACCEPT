private static PointTransform_F64 adjustmentTransform_F64(IntrinsicParameters param,
															  IntrinsicParameters paramAdj,
															  boolean adjToDistorted,
															  PointTransform_F64 remove_p_to_p,
															  DenseMatrix64F A) {
		DenseMatrix64F A_inv = null;

		if( !adjToDistorted || paramAdj != null ) {
			A_inv = new DenseMatrix64F(3, 3);
			if (!CommonOps.invert(A, A_inv)) {
				throw new RuntimeException("Failed to invert adjustment matrix.  Probably bad.");
			}
		}

		if( paramAdj != null ) {
			PerspectiveOps.adjustIntrinsic(param, A_inv, paramAdj);
		}

		if( adjToDistorted ) {
			PointTransform_F64 add_p_to_p = distortTransform(param).distort_F64(true, true);
			PointTransformHomography_F64 adjust = new PointTransformHomography_F64(A);

			return new SequencePointTransform_F64(adjust,add_p_to_p);
		} else {
			PointTransformHomography_F64 adjust = new PointTransformHomography_F64(A_inv);

			return new SequencePointTransform_F64(remove_p_to_p,adjust);
		}
	}