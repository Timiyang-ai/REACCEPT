	@Test
	void retrieveBPartnerLocation()
	{
		// invoke the method under test
		final ResponseEntity<JsonResponseLocation> result = bpartnerRestController.retrieveBPartnerLocation(
				"ext-" + C_BPARTNER_EXTERNAL_ID,
				"gln-" + C_BPARTNER_LOCATION_GLN);

		assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		final JsonResponseLocation resultBody = result.getBody();

		expect(resultBody).toMatchSnapshot();
	}