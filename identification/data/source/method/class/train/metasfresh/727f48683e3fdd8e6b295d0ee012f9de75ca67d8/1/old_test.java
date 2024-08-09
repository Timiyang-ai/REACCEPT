	@Test
	public void test_buildAddressString_0010()
	{
		final I_C_Location location = prepareLocation("addr1", "addr2", null, null, "City1", "Region1", "121212", "", prepareCountry("Germany", "@A1@ @A2@ @C@ @CO@"));
		final boolean isLocalAddress = true;
		final String bPartnerBlock = null;
		final String userBlock = null;
		Assert.assertEquals(
				"LOCAL: addr1\naddr2\nCity1\nGermany",
				builder("de_CH")
						.buildAddressString(location, isLocalAddress, bPartnerBlock, userBlock));
	}