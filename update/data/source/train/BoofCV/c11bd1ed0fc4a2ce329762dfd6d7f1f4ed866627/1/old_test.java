@Test
	public void boundBox_check() {

		// basic sanity check
		Affine2D_F32 affine = new Affine2D_F32(1,0,0,1,2,3);
		PixelTransformAffine_F32 transform = new PixelTransformAffine_F32(affine);
		Rectangle2D_I32 found = DistortImageOps.boundBox(10,20,30,40,transform);
		
		assertEquals(2,found.tl_x);
		assertEquals(3,found.tl_y);
		assertEquals(10,found.width);
		assertEquals(20,found.height);
		
		// bottom right border
		found = DistortImageOps.boundBox(10,20,8,18,transform);
		assertEquals(2,found.tl_x);
		assertEquals(3,found.tl_y);
		assertEquals(6,found.width);
		assertEquals(15,found.height);
		
		// top right border
		affine.set(new Affine2D_F32(1,0,0,1,-2,-3));
		found = DistortImageOps.boundBox(10,20,8,18,transform);
		assertEquals(0,found.tl_x);
		assertEquals(0,found.tl_y);
		assertEquals(8,found.width);
		assertEquals(17,found.height);
	}