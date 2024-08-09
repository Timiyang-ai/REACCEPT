	public void test_getValueNamePairs() throws Exception
	{
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(MTable.ACCESSLEVEL_AD_Reference_ID);
		final String sql = "SELECT Value, Name FROM AD_Ref_List WHERE AD_Reference_ID=? ORDER BY Value";
		// Get (with optional item)
		ValueNamePair[] arr = DB.getValueNamePairs(sql, true, params);
		assertEquals("Invalid size", 6+1, arr.length);
		assertSame("First value should be EMPTY", ValueNamePair.EMPTY, arr[0]);
		assertEquals(arr[1].getValue(), "1");
		assertEquals(arr[2].getValue(), "2");
		assertEquals(arr[3].getValue(), "3");
		assertEquals(arr[4].getValue(), "4");
		assertEquals(arr[5].getValue(), "6");
		assertEquals(arr[6].getValue(), "7");
		// Get (NO optional item)
		arr = DB.getValueNamePairs(sql, false, params);
		assertEquals("Invalid size", 6, arr.length);
		assertEquals(arr[0].getValue(), "1");
		assertEquals(arr[1].getValue(), "2");
		assertEquals(arr[2].getValue(), "3");
		assertEquals(arr[3].getValue(), "4");
		assertEquals(arr[4].getValue(), "6");
		assertEquals(arr[5].getValue(), "7");
	}