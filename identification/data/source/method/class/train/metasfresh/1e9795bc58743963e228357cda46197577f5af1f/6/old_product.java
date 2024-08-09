@Override
	public ResponseEntity<JsonBPartnerComposite> retrieveBPartner(
			@ApiParam(value = BPARTER_IDENTIFIER_DOC, allowEmptyValue = false) //
			@NonNull final String bpartnerIdentifier)
	{
		final JsonBPartnerComposite result = MockDataUtil.createMockBPartnerComposite(bpartnerIdentifier);
		return ResponseEntity.ok(result);
	}