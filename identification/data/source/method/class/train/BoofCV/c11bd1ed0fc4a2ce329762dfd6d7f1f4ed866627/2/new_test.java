@Test
	public void updateTrackLocation() {
		ImageFloat32 a = new ImageFloat32(100,100);
		ImageFloat32 b = new ImageFloat32(100,100);

		// randomize input image and move it
		GImageMiscOps.fillUniform(a,rand,0,200);
		GImageMiscOps.fillUniform(b,rand,0,200);
		shiftCopy(0,0,a,b);

		CirculantTracker<ImageFloat32> alg = new CirculantTracker<ImageFloat32>(1f/16,0.2,1e-2,0.075,1.0,64,255,interp);
		alg.initialize(a,5,6,20,25);

		alg.updateTrackLocation(b);

		// only pixel level precision.
		float tolerance = 1f;

		// No motion motion
		Rectangle2D_F32 r = alg.getTargetLocation();
		assertEquals(5,r.x0,tolerance);
		assertEquals(6,r.y0,tolerance);

		// check estimated motion
		GImageMiscOps.fillUniform(b,rand,0,200);
		shiftCopy(-3,2,a,b);
		alg.updateTrackLocation(b);
		r = alg.getTargetLocation();
		assertEquals(5-3,r.x0,tolerance);
		assertEquals(6+2,r.y0,tolerance);

		// try out of bounds case
		GImageMiscOps.fillUniform(b,rand,0,200);
		shiftCopy(-6,0,a,b);
		alg.updateTrackLocation(b);
		assertEquals(5-6,r.x0,tolerance);
		assertEquals(6,r.y0,tolerance);
	}