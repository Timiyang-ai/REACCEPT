@Test
	public void setDescription_compare() {

		ImageMiscOps.fillUniform(image,rand,0,100);
		GradientSobel.process(image, derivX, derivY, new ImageBorder1D_F32(BorderIndex1D_Extend.class));

		KltTracker<ImageFloat32, ImageFloat32> tracker = createDefaultTracker();
		tracker.setImage(image, derivX, derivY);

		KltFeature featureA = new KltFeature(3);
		KltFeature featureB = new KltFeature(3);

		featureA.setPosition(20.6f,25.1f);
		featureB.setPosition(20.6f,25.1f);

		tracker.setAllowedBounds(featureA);
		tracker.internalSetDescription(featureA);
		tracker.internalSetDescriptionBorder(featureB);

		for( int i = 0; i < featureA.desc.data.length; i++ ) {
			assertTrue(featureA.desc.data[i] == featureB.desc.data[i]);
			assertTrue(featureA.derivX.data[i] == featureB.derivX.data[i]);
			assertTrue(featureA.derivY.data[i] == featureB.derivY.data[i]);
		}
	}