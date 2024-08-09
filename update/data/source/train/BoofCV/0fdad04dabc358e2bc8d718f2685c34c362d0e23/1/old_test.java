@Test(expected = IllegalArgumentException.class)
	public void setTo_mismatch() {
		DummyImage a = new DummyImage(10, 20);
		DummyImage b = new DummyImage(11, 20);

		a.setTo(b);
	}