@Test
	public void updateTrackLocation() {
		ImageFloat32 a = new ImageFloat32(30,35);
		ImageFloat32 b = new ImageFloat32(30,35);

		// randomize input image and move it
		GImageMiscOps.fillUniform(a,rand,0,200);
		GImageMiscOps.fillUniform(b,rand,0,200);
		shiftCopy(0,0,a,b);

		CirculantTracker alg = new CirculantTracker(1f/16f,0.2f,1e-2f,0.075f,255f);
		alg.initialize(a,5,6,20,25);

		alg.updateTrackLocation(b);

		// not super precise...
		int tolerance = 0;

		// No motion motion
		Rectangle2D_I32 r = alg.getTargetLocation();
		assertEquals(5,r.tl_x,tolerance);
		assertEquals(6,r.tl_y,tolerance);

		// check estimated motion
		GImageMiscOps.fillUniform(b,rand,0,200);
		shiftCopy(-3,2,a,b);
		alg.updateTrackLocation(b);
		r = alg.getTargetLocation();
		assertEquals(5-3,r.tl_x,tolerance);
		assertEquals(6+2,r.tl_y,tolerance);

		// try out of bounds case
		GImageMiscOps.fillUniform(b,rand,0,200);
		shiftCopy(-6,0,a,b);
		alg.updateTrackLocation(b);
		assertEquals(0,r.tl_x,tolerance);
		assertEquals(6,r.tl_y,tolerance);
	}