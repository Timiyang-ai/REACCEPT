	@Test
	public void convertSizeBytes() {
		assertThat(UnitConverter.toBytes("12345")).isEqualTo(12345);
		assertThat(UnitConverter.toBytes("12b")).isEqualTo(12);
		assertThat(UnitConverter.toBytes("12k")).isEqualTo(12 * 1024);
		assertThat(UnitConverter.toBytes("12M")).isEqualTo(12 * 1024 * 1024);

		assertThat(UnitConverter.toBytes("12G")).isEqualTo(12l * 1024 * 1024 * 1024);
		assertThat(UnitConverter.toBytes("12T")).isEqualTo(12l * 1024 * 1024 * 1024 * 1024);

		try {
			UnitConverter.toBytes("12x");
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}

		try {
			UnitConverter.toBytes("a12");
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}
	}