@Test
	public void setTo_mismatch() {
		DummyImage a = new DummyImage(10, 20);
		DummyImage b = new DummyImage(11, 21);

		a.setTo(b);

		assertEquals(a.width, 11);
		assertEquals(b.height, 21);
	}