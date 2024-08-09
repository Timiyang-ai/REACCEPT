@Test
	public void get_set() {
		ImageBase img = createImage(10, 20);
		setRandom(img);

		Number expected = randomNumber();
		Number orig = (Number) call(img, "get", 0, null, 1, 1);

		// make sure the two are not equal
		assertFalse(expected.equals(orig));

		// set the expected to the point in the image
		call(img, "set", 1, expected, 1, 1);
		Number found = (Number) call(img, "get", 0, null, 1, 1);
		if (GeneralizedImageOps.isFloatingPoint(img))
			assertEquals(expected.doubleValue(), found.doubleValue(), 1e-4);
		else {
			if( ((ImageInteger)img).isSigned() )
				assertTrue(expected.intValue() == found.intValue());
			else
				assertTrue((expected.intValue() & 0xFFFF) == found.intValue());
		}
	}