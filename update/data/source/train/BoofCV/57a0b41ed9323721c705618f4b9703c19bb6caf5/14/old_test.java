@Test
	public void easyTest() {
		T input = imageType.createImage(30,40);
		ImageSInt32 output = new ImageSInt32(30,40);

		GImageMiscOps.fillRectangle(input, 100, 0, 0, 15, 40);

		SegmentSlic<T> alg = createAlg(12,200,10, ConnectRule.EIGHT );

		alg.process(input,output);

		GrowQueue_I32 memberCount = alg.getRegionMemberCount();
		checkUnique(alg,output,memberCount.size);

		// see if the member count is correctly computed
		GrowQueue_I32 foundCount = new GrowQueue_I32(memberCount.size);
		foundCount.resize(memberCount.size);
		ImageSegmentationOps.countRegionPixels(output, foundCount.size, foundCount.data);
		for (int i = 0; i < memberCount.size; i++) {
			assertEquals(memberCount.get(i),foundCount.get(i));
		}
	}