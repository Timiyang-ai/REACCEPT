@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated bpartner(s)"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@PutMapping
	@Override
	public ResponseEntity<JsonUpsertResponse> createOrUpdateBPartner(
			// the requestBody annotation needs to be present it here; otherwise, at least swagger doesn't get it
			@RequestBody @NonNull final JsonBPartnerUpsertRequest bpartners)
	{
		final JsonUpsertResponseBuilder response = JsonUpsertResponse.builder();

		for (final JsonBPartnerUpsertRequestItem requestItem : bpartners.getRequestItems())
		{
			final JsonUpsertResponseItem responseItem = JsonUpsertResponseItem.builder()
					.externalId(requestItem.getExternalId())
					.metasfreshId(MockDataUtil.nextMetasFreshId())
					.build();
			response.responseItem(responseItem);
		}
		return new ResponseEntity<>(response.build(), HttpStatus.CREATED);
	}