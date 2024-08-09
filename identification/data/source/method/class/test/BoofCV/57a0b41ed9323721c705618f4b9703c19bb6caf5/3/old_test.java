@Test
	public void setImage() {
		ImageFloat32 image = new ImageFloat32(640,480);
		GImageMiscOps.fillUniform(image,rand,0,200);


		UnrollSiftScaleSpaceGradient alg = new UnrollSiftScaleSpaceGradient(new SiftScaleSpace(-1,3,3,2));
		alg.setImage(image);

		SiftScaleSpace ss = new SiftScaleSpace(-1,3,3,2);
		ss.initialize(image);

		ImageFloat32 derivX = new ImageFloat32(640,480);
		ImageFloat32 derivY = new ImageFloat32(640,480);

		int total = 0;
		do {
			for (int i = 0; i < ss.getNumScales(); i++, total++ ) {
				ImageFloat32 scaleImage = ss.getImageScale(i);

				derivX.reshape(scaleImage.width,scaleImage.height);
				derivY.reshape(scaleImage.width,scaleImage.height);

				GImageDerivativeOps.gradient(DerivativeType.THREE,scaleImage,derivX,derivY, BorderType.EXTENDED);

				UnrollSiftScaleSpaceGradient.ImageScale found = alg.usedScales.get(total);

				BoofTesting.assertEquals(derivX,found.derivX,1e-4);
				BoofTesting.assertEquals(derivY,found.derivY,1e-4);
				assertEquals(ss.computeSigmaScale(i),found.sigma,1e-4);
				assertEquals(image.width/(double)scaleImage.width,found.imageToInput,1e-4);

			}

		} while( ss.computeNextOctave() );
	}