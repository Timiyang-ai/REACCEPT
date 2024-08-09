	@Test
	public void setInSampleSize() {
		gifOptions.setInSampleSize(2);
		assertThat(gifOptions.inSampleSize).isEqualTo((char) 2);
	}