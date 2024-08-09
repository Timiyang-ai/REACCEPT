@Test
	public void testExtractURLParameterValues() {
		Map<String,Class<?>> types = new HashMap<String,Class<?>>();
		types.put("contactId", Integer.class);
		types.put("addressId", Long.class);
		String path = "/contacts/{contactId}/addresses/{addressId}";
		String requestedPath = "/contacts/33445/addresses/12";
		String result = URITemplateHelper.replaceURLTemplateIDs(path,types);
		Assert.assertTrue("/contacts/(\\d+)/addresses/(\\d+)$".equals(result));
		Map<Integer,String> positions = URITemplateHelper.extractURLParameterValuesFromRequest(requestedPath, Pattern.compile(result));
		Assert.assertFalse(positions.isEmpty());
		Assert.assertTrue(positions.get(new Integer(1)).equals("33445"));
		Assert.assertTrue(positions.get(new Integer(2)).equals("12"));
	}