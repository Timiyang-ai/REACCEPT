	@Test
	public void rpadZero_EmptyStringTest()
	{
		final String emptyString = "";
		final String zeroString = "0000000000";

		assertThat(StringUtils.rpadZero(emptyString, 10, "This Is An Empty String")).isEqualTo(zeroString);
	}