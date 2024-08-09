@Test
	public void process() {
		// what the initial transform should be
		Se2_F32 computed = new Se2_F32(4,5,6);
		Se2_F32 model = new Se2_F32();
		DummyTracker tracker = new DummyTracker();
		DummyModelMatcher<Se2_F32> matcher = new DummyModelMatcher<Se2_F32>(computed,5);

		ImageUInt8 input = new ImageUInt8(20,30);

		ImageMotionPointTrackerKey<ImageUInt8,Se2_F32> alg =
				new ImageMotionPointTrackerKey<ImageUInt8,Se2_F32>(tracker,matcher,null,model,1000);

		// the first time it processes an image it should always return false since no motion can be estimated
		assertFalse(alg.process(input));
		assertFalse(alg.isKeyFrame());
		assertEquals(0, tracker.numSpawn);

		// make the current frame into the keyframe
		// request that the current frame is a keyframe
		alg.changeKeyFrame();
		assertEquals(0, tracker.numDropAll);
		assertEquals(1, tracker.numSpawn);
		assertTrue(alg.isKeyFrame());

		// now it should compute some motion
		assertTrue(alg.process(input));
		assertFalse(alg.isKeyFrame());

		// no new tracks should have been spawned
		assertEquals(1, tracker.numSpawn);

		// test the newly computed results
		assertEquals(computed.getX(), alg.getKeyToCurr().getX(), 1e-8);
		assertEquals(computed.getX(), alg.getWorldToCurr().getX(), 1e-8);

		// see if reset does its job
		assertEquals(0, tracker.numDropAll);
		alg.reset();
		assertEquals(1, tracker.numDropAll);
		assertEquals(0, alg.getTotalFramesProcessed() );
		assertEquals(0, alg.getKeyToCurr().getX(), 1e-8);
		assertEquals(0, alg.getWorldToCurr().getX(), 1e-8);
	}