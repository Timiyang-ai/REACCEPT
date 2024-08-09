@Override
	public ResponseEntity<JsonUpsertResponseItem> createOrUpdateLocation(
			// the requestBody annotation needs to be present it here; otherwise, at least swagger doesn't get it
			@RequestBody @NonNull final JsonBPartnerLocation location)
	{
		final JsonUpsertResponseItem resonseItem = JsonUpsertResponseItem.builder()
				.externalId(location.getExternalId())
				.metasfreshId(MockDataUtil.nextMetasFreshId())
				.build();
		return new ResponseEntity<>(resonseItem, HttpStatus.CREATED);
	}