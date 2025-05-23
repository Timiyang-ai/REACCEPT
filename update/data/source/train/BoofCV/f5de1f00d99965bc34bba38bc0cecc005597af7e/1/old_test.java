@Test
	public void process() {
		// what the initial transform should be
		Se2_F32 initial = new Se2_F32(1,2,3);
		Se2_F32 computed = new Se2_F32(4,5,6);
		Se2_F32 model = new Se2_F32();
		DummyTracker tracker = new DummyTracker();
		DummyModelMatcher<Se2_F32> matcher = new DummyModelMatcher<Se2_F32>(computed,5);

		ImageUInt8 input = new ImageUInt8(20,30);

		ImageMotionPointKey<ImageUInt8,Se2_F32> alg = new ImageMotionPointKey<ImageUInt8,Se2_F32>(tracker,matcher,null,model);

		// specify an initial transform
		alg.setInitialTransform(initial);

		// the first time it processes an image it should always return false since no motion can be estimated
		assertFalse(alg.process(input));
		assertEquals(0, tracker.numSpawn);

		// make the current frame into the keyframe
		// request that the current frame is a keyframe
		alg.changeKeyFrame();
		assertEquals(1, tracker.numSpawn);

		// now it should compute some motion
		assertTrue(alg.process(input));

		// no new tracks should have been spawned
		assertEquals(1, tracker.numSpawn);

		// test the newly computed results
		Se2_F32 keyToCurr = alg.getKeyToCurr();
		assertEquals(computed.getX(), keyToCurr.getX(), 1e-8);

		// see if this transform was correctly computed
		Se2_F32 worldToCurr = initial.concat(keyToCurr, null);
		Se2_F32 found = alg.getWorldToCurr();
		assertEquals(worldToCurr.getX(), found.getX(), 1e-8);
	}