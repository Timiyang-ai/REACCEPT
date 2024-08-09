@Test
	public void fit_perfect_affine() {
		// distorted and undistorted views
		Affine2D_F64 affines[] = new Affine2D_F64[2];
		affines[0] = new Affine2D_F64();
		affines[1] = new Affine2D_F64(1.3,0.05,-0.15,0.87,0.1,0.6);
		ConvertTransform_F64.convert(new Se2_F64(0,0,0.2),affines[0]);

		for (Class imageType : imageTypes) {
			for (Affine2D_F64 a : affines) {
//				System.out.println(imageType+"  "+a);
				fit_perfect_affine(true, a, imageType);
				fit_perfect_affine(false, a, imageType);
			}
		}
	}