@Test
	public void testRequest() throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		// Auth required
		TwilioRestResponse response = client.request("/2010-04-01/Accounts.json", "GET", null);	
		assertEquals(401, response.getHttpStatus());
		
		// Auth not required
		response = client.request("/2010-04-01", "GET", null);
		assertEquals(200, response.getHttpStatus());
		
		// 404'd
		response = client.request("/asfhrhewhwejrkasyrey", "GET", null);
		assertEquals(404, response.getHttpStatus());	
	}