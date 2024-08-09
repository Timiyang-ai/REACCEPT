	@Test
	public void toByteArray() {
		byte[] data = new byte[16];
		for (int i = 0; i < data.length; ++i) {
			data[i] = (byte)(Math.random()*256);
		}
		BitChromosome bc = new BitChromosome(data);

		Assert.assertEquals(bc.toByteArray(), data);

	}