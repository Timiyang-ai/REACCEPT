@Test
	public void processFeatureCandidate_Shift() {
		ImageFloat32 upper = new ImageFloat32(30,40);
		ImageFloat32 current = new ImageFloat32(30,40);
		ImageFloat32 lower = new ImageFloat32(30,40);

		SiftDetector alg = createDetector();
		alg.pixelScaleToInput = 2.0;
		alg.sigmaLower = 4; alg.sigmaTarget = 5; alg.sigmaUpper = 6;
		alg.dogLower = lower; alg.dogTarget = current; alg.dogUpper = upper;
		alg.derivXX.setImage(current); alg.derivXY.setImage(current); alg.derivYY.setImage(current);

		int x = 15,y = 16;
		for( float sign :  new float[]{-1,1} ) {
			alg.detections.reset();
			current.set(x, y-1, sign*90);
			current.set(x, y, sign*100);
			current.set(x, y+1, sign*80);
			current.set(x-1, y, sign*90);
			current.set(x+1, y, sign*80);
			upper.set(x, y, sign*80);
			lower.set(x, y, sign*90);

			alg.processFeatureCandidate(15, 16, 100, sign > 0);

			ScalePoint p = alg.getDetections().get(0);
			// make sure it is close
			assertTrue(Math.abs(x * 2 - p.x) < 2);
			assertTrue(Math.abs(y * 2 - p.y) < 2);
			assertTrue(Math.abs(5 - p.scale) < 2);

			// see if its shifted in the correct direction
			assertTrue( x*2 > p.x );
			assertTrue( y*2 > p.y );
			assertTrue( 5 > p.scale );

			// do a test just for scale since the code branches depending on the sign
			upper.set(x, y, sign*90);
			lower.set(x, y, sign*80);

			alg.detections.reset();
			alg.processFeatureCandidate(15, 16, 100, sign > 0);
			assertTrue(Math.abs(5 - p.scale) < 2);
			assertTrue( 5 < p.scale );

		}
	}