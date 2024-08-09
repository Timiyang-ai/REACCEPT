@Override
	public ResponseEntity<JsonUpsertResponseItem> createOrUpdateContact(
			@ApiParam(value = BPARTER_IDENTIFIER_DOC, allowEmptyValue = false) //
			@NonNull final String bpartnerIdentifier,

			@RequestBody @NonNull final JsonContact contact)
	{
		final JsonUpsertResponseItem resonseItem = JsonUpsertResponseItem.builder()
				.externalId(contact.getExternalId())
				.metasfreshId(MockDataUtil.nextMetasFreshId())
				.build();
		return new ResponseEntity<>(resonseItem, HttpStatus.CREATED);
	}