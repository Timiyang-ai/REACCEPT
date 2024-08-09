@Override
	public ResponseEntity<JsonUpsertResponseItem> createOrUpdateLocation(
			@ApiParam(value = BPARTER_IDENTIFIER_DOC, allowEmptyValue = false) //
			@NonNull final String bpartnerIdentifier,

			@RequestBody @NonNull final JsonBPartnerLocation location)
	{
		final JsonUpsertResponseItem resonseItem = JsonUpsertResponseItem.builder()
				.externalId(location.getExternalId())
				.metasfreshId(MockDataUtil.nextMetasFreshId())
				.build();
		return new ResponseEntity<>(resonseItem, HttpStatus.CREATED);
	}