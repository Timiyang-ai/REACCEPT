	public void test_getKeyNamePairs() throws Exception
	{
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(MTable.ACCESSLEVEL_AD_Reference_ID);
		final String sql = "SELECT AD_Ref_List_ID, Value FROM AD_Ref_List WHERE AD_Reference_ID=? ORDER BY Value";
		// Get (with optional item)
		KeyNamePair[] arr = DB.getKeyNamePairs(sql, true, params);
		assertEquals("Invalid size", 6+1, arr.length);
		assertSame("First value should be EMPTY", KeyNamePair.EMPTY, arr[0]);
		assertEquals(arr[1].getName(), "1");
		assertEquals(arr[2].getName(), "2");
		assertEquals(arr[3].getName(), "3");
		assertEquals(arr[4].getName(), "4");
		assertEquals(arr[5].getName(), "6");
		assertEquals(arr[6].getName(), "7");
		// Get (NO optional item)
		arr = DB.getKeyNamePairs(sql, false, params);
		assertEquals("Invalid size", 6, arr.length);
		assertEquals(arr[0].getName(), "1");
		assertEquals(arr[1].getName(), "2");
		assertEquals(arr[2].getName(), "3");
		assertEquals(arr[3].getName(), "4");
		assertEquals(arr[4].getName(), "6");
		assertEquals(arr[5].getName(), "7");
	}