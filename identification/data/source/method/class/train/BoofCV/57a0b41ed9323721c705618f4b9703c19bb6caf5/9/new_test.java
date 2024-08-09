@Test
	public void testScale() {
		GrayF32 input = createImage(width,height);

		DescribePointBriefSO<GrayF32> alg = createAlg();
		TupleDesc_B desc1 = alg.createFeature();
		TupleDesc_B desc2 = alg.createFeature();

		alg.setImage(input);
		alg.process(input.width/2,input.height/2,0,briefRadius,desc1);
		alg.process(input.width/2,input.height/2,0,2*briefRadius,desc2);

		boolean identical = true;
		for( int i = 0; i < desc1.data.length; i++ ) {
			if( desc1.data[i] != desc2.data[i] )
				identical = false;
		}

		assertFalse(identical);
	}