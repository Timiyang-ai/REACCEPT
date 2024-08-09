@Override
	public ResponseEntity<JsonBPartnerLocation> retrieveBPartnerLocation(

			@ApiParam(value = BPARTER_IDENTIFIER_DOC, allowEmptyValue = false) //
			@NonNull final String bpartnerIdentifier,

			@ApiParam(value = LOCATION_IDENTIFIER_DOC, allowEmptyValue = false) //
			@NonNull final String locationIdentifier)
	{
		final JsonBPartnerLocation location = MockDataUtil.createMockLocation("l1", "CH");
		return ResponseEntity.ok(location);
	}