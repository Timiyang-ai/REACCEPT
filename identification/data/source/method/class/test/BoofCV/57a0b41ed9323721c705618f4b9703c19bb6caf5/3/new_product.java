public void setImage(GrayF32 image) {

		scaleSpace.initialize(image);

		usedScales.clear();
		do {
			for (int i = 0; i < scaleSpace.getNumScales(); i++) {
				GrayF32 scaleImage = scaleSpace.getImageScale(i);
				double sigma = scaleSpace.computeSigmaScale(i);
				double pixelCurrentToInput = scaleSpace.pixelScaleCurrentToInput();

				ImageScale scale = allScales.get(usedScales.size());
				scale.derivX.reshape(scaleImage.width,scaleImage.height);
				scale.derivY.reshape(scaleImage.width,scaleImage.height);

				gradient.process(scaleImage,scale.derivX,scale.derivY);
				scale.imageToInput = pixelCurrentToInput;
				scale.sigma = sigma;

				usedScales.add(scale);
			}
		} while( scaleSpace.computeNextOctave() );
	}