public static CameraPinhole estimatePinhole(Point2Transform2_F64 pixelToNorm , int width , int height ) {

		Point2D_F64 normA = new Point2D_F64();
		Point2D_F64 normB = new Point2D_F64();
		Vector3D_F64 vectorA = new Vector3D_F64();
		Vector3D_F64 vectorB = new Vector3D_F64();

		pixelToNorm.compute(0,height/2,normA);
		pixelToNorm.compute(width,height/2,normB);
		vectorA.set(normA.x,normA.y,1);
		vectorB.set(normB.x,normB.y,1);
		double hfov = UtilVector3D_F64.acute(vectorA,vectorB);

		pixelToNorm.compute(width/2,0,normA);
		pixelToNorm.compute(width/2,height,normB);
		vectorA.set(normA.x,normA.y,1);
		vectorB.set(normB.x,normB.y,1);
		double vfov = UtilVector3D_F64.acute(vectorA,vectorB);

		CameraPinhole intrinsic = new CameraPinhole();
		intrinsic.width = width;
		intrinsic.height = height;
		intrinsic.skew = 0;
		intrinsic.cx = width/2;
		intrinsic.cy = height/2;
		intrinsic.fx = intrinsic.cx/Math.tan(hfov/2);
		intrinsic.fy = intrinsic.cy/Math.tan(vfov/2);

		return intrinsic;
	}