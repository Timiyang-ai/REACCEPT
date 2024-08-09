	@Test
	public void lpadZero_EmptyStringTest()
	{
		final String emptyString = "";
		final String zeroString = "0000000000";

		assertThat(StringUtils.lpadZero(emptyString, 10, "This Is An Empty String")).isEqualTo(zeroString);
	}