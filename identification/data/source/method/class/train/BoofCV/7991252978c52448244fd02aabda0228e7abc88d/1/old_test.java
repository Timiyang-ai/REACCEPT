@Test
	public void changeKeyFrame() {
		Se2_F32 computed = new Se2_F32(4,5,6);
		Se2_F32 model = new Se2_F32();
		DummyTracker tracker = new DummyTracker();
		DummyModelMatcher<Se2_F32> matcher = new DummyModelMatcher<Se2_F32>(computed,5);
		
		ImageUInt8 input = new ImageUInt8(20,30);

		ImageMotionPointKey<ImageUInt8,Se2_F32> alg = new ImageMotionPointKey<ImageUInt8,Se2_F32>(tracker,matcher,null,model);
		
		// process twice to change the transforms
		alg.process(input);
		alg.process(input);

		// sanity check
		Se2_F32 worldToKey = alg.getWorldToKey();
		assertEquals(0,worldToKey.getX(),1e-8);
		assertEquals(1,tracker.numSpawn);
		
		// invoke the function being tested
		alg.changeKeyFrame();
		
		// the keyframe should be changed and new tracks spawned
		assertEquals(1,tracker.numSetKeyframe);
		assertEquals(2,tracker.numSpawn);
		
		// worldToKey should now be equal to worldToCurr
		worldToKey = alg.getWorldToKey();
		assertEquals(computed.getX(),worldToKey.getX(),1e-8);
	}