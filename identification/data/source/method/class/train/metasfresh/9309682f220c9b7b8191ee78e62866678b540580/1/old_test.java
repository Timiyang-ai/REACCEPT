@Test
	public void test_buildAddressString_0070()
	{
		I_C_Location location = prepareLocation("addr1", "addr2", null, null, "City1", "Region1", "121212", false, "",
				prepareCountry("Country1", "@A1@ @A2@ @P@ @C@ (Region @R@) @CO@"));
		boolean isLocalAddress = false;
		final String bPartnerBlock = null;
		final String userBlock = null;

		Assert.assertEquals(
				"addr1\naddr2\n121212 City1\nRegion Region1 Country1",
				builder.buildAddressString(location, isLocalAddress, bPartnerBlock, userBlock)
				);

		isLocalAddress = true;

		Assert.assertEquals(
				"LOCAL: addr1\naddr2\n121212 City1\nRegion Region1",
				builder.buildAddressString(location, isLocalAddress, bPartnerBlock, userBlock)
				);
	}