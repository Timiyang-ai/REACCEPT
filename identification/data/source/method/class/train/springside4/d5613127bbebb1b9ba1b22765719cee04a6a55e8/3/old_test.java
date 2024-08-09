	@Test
	public void convertDurationMillis() {
		assertThat(UnitConverter.toDurationMillis("12345")).isEqualTo(12345);
		assertThat(UnitConverter.toDurationMillis("12S")).isEqualTo(12000);
		assertThat(UnitConverter.toDurationMillis("12s")).isEqualTo(12000);
		assertThat(UnitConverter.toDurationMillis("12ms")).isEqualTo(12);
		assertThat(UnitConverter.toDurationMillis("12m")).isEqualTo(12 * 60 * 1000);
		assertThat(UnitConverter.toDurationMillis("12h")).isEqualTo(12l * 60 * 60 * 1000);
		assertThat(UnitConverter.toDurationMillis("12d")).isEqualTo(12l * 24 * 60 * 60 * 1000);

		try {
			assertThat(UnitConverter.toDurationMillis("12a")).isEqualTo(12 * 60 * 1000);
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}

		try {
			assertThat(UnitConverter.toDurationMillis("a12")).isEqualTo(12 * 60 * 1000);
			fail("should fail");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(IllegalArgumentException.class);
		}
	}