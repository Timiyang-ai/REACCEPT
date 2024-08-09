	@Test
	void retrieveBPartner_ext()
	{
		// invoke the method under test
		final ResponseEntity<JsonResponseComposite> result = bpartnerRestController.retrieveBPartner("ext-" + C_BPARTNER_EXTERNAL_ID);

		assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		final JsonResponseComposite resultBody = result.getBody();

		expect(resultBody).toMatchSnapshot();
	}