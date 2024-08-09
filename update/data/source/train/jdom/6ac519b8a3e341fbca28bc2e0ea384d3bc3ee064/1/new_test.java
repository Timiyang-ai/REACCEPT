@Test
	public void testCheckAttributeName() {
		//check out of range values
		assertNotNull("validated invalid null", Verifier.checkAttributeName(null));
		assertNotNull("validated invalid name with null", Verifier.checkAttributeName("test" + (char)0x0));
		assertNotNull("validated invalid name with null", Verifier.checkAttributeName("test" + (char)0x0 + "ing"));
		assertNotNull("validated invalid name with null", Verifier.checkAttributeName((char)0x0 + "test"));
		assertNotNull("validated invalid name with 0x01", Verifier.checkAttributeName((char)0x01 + "test"));
		assertNotNull("validated invalid name with 0xD800", Verifier.checkAttributeName("test" + (char)0xD800));
		assertNotNull("validated invalid name with 0xD800", Verifier.checkAttributeName("test" + (char)0xD800 + "ing"));
		assertNotNull("validated invalid name with 0xD800", Verifier.checkAttributeName((char)0xD800 + "test"));
		assertNotNull("validated invalid name with :", Verifier.checkAttributeName("test" + ':' + "local"));
		assertNotNull("validated invalid name with xml:lang", Verifier.checkAttributeName("xml:lang"));
		assertNotNull("validated invalid name with xml:space", Verifier.checkAttributeName("xml:space"));

		//invalid start characters
		assertNotNull("validated invalid name with startin -", Verifier.checkAttributeName('-' + "test"));
		assertNotNull("validated invalid name with xmlns", Verifier.checkAttributeName("xmlns"));
		assertNotNull("validated invalid name with startin :", Verifier.checkAttributeName(':' + "test"));

		//valid tests
		assertNull("invalidated valid name with starting _", Verifier.checkAttributeName('_' + "test"));
		assertNull("invalidated valid name with _", Verifier.checkAttributeName("test" + '_'));
		assertNull("invalidated valid name with .", Verifier.checkAttributeName("test" + '.' + "name"));
		assertNull("invalidated valid name with 0x00B7", Verifier.checkAttributeName("test" + (char)0x00B7));
		assertNull("invalidated valid name with 0x4E01", Verifier.checkAttributeName("test" + (char)0x4E01));
		assertNull("invalidated valid name with 0x0301", Verifier.checkAttributeName("test" + (char)0x0301));

    	assertNull(Verifier.checkAttributeName("hi"));
    	assertNull(Verifier.checkAttributeName("hi2you"));
    	assertNull(Verifier.checkAttributeName("hi_you"));
    	
    	assertNotNull(Verifier.checkAttributeName(null));
    	assertNotNull(Verifier.checkAttributeName(""));
    	assertNotNull(Verifier.checkAttributeName("   "));
    	assertNotNull(Verifier.checkAttributeName("  hi  "));
    	assertNotNull(Verifier.checkAttributeName("hi "));
    	assertNotNull(Verifier.checkAttributeName(" hi"));
    	assertNotNull(Verifier.checkAttributeName("2bad"));

    }