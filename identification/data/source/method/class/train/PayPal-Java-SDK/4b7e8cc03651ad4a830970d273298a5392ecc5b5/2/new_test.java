	@Test
	public void executeTest() throws InvalidResponseDataException,
			HttpErrorException, ClientActionRequiredException, IOException,
			InterruptedException {
		httpConfiguration
				.setEndPointUrl("https://svcs.sandbox.paypal.com/AdaptivePayments/ConvertCurrency");
		connection.createAndconfigureHttpConnection(httpConfiguration);
		String response = connection.execute("url", "payload", null);
		Assert.assertTrue(response.contains("<ack>Failure</ack>"));
	}