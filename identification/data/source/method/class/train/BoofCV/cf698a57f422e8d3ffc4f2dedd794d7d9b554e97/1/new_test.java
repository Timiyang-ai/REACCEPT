@Test
	public void boundBox_check() {

		Point2D_F32 work = new Point2D_F32();

		// basic sanity check
		Affine2D_F32 affine = new Affine2D_F32(1,0,0,1,2,3);
		PixelTransformAffine_F32 transform = new PixelTransformAffine_F32(affine);
		RectangleLength2D_I32 found = DistortImageOps.boundBox(10,20,30,40,work,transform);
		
		assertEquals(2,found.x0);
		assertEquals(3,found.y0);
		assertEquals(10,found.width);
		assertEquals(20,found.height);
		
		// bottom right border
		found = DistortImageOps.boundBox(10,20,8,18,work,transform);
		assertEquals(2,found.x0);
		assertEquals(3,found.y0);
		assertEquals(6,found.width);
		assertEquals(15,found.height);
		
		// top right border
		affine.set(new Affine2D_F32(1,0,0,1,-2,-3));
		found = DistortImageOps.boundBox(10,20,8,18,work,transform);
		assertEquals(0,found.x0);
		assertEquals(0,found.y0);
		assertEquals(8,found.width);
		assertEquals(17,found.height);
	}