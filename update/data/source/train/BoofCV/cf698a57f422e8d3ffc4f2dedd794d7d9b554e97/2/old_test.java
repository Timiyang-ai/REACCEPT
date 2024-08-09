@Test
	public void centerBoxInside() {

		// easy cases
		checkInsideBorder(new Affine2D_F64(1,0,0,1,1,2),false);
		checkInsideBorder(new Affine2D_F64(1,0,0,2,1,2),false);

		// rotated by 90 degrees, still easy
		checkInsideBorder(new Affine2D_F64(0,-1,1,0,1,2),false);
		checkInsideBorder(new Affine2D_F64(-1,0,0,-1,1,2),false);

		// when not square the hard rules above doesn't work any more because by design some outside is included
		// inside as a compromise
	}