@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated bpartner(s)"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@PutMapping
	@Override
	public ResponseEntity<JsonUpsertResponse> createOrUpdateBPartner(
			@RequestBody @NonNull final JsonBPartnerUpsertRequest bpartners)
	{
		final JsonPersisterService persister = jsonServiceFactory.createPersister();

		final SyncAdvise defaultSyncAdvise = bpartners.getSyncAdvise();

		final JsonUpsertResponseBuilder response = JsonUpsertResponse.builder();

		for (final JsonBPartnerUpsertRequestItem requestItem : bpartners.getRequestItems())
		{
			final BPartnerComposite syncToMetasfresh = persister.persist(
					requestItem.getEffectiveBPartnerComposite(),
					defaultSyncAdvise);

			final MetasfreshId metasfreshId = MetasfreshId.of(syncToMetasfresh.getBpartner().getId());

			final JsonUpsertResponseItem responseItem = JsonUpsertResponseItem.builder()
					.externalId(requestItem.getExternalId())
					.metasfreshId(metasfreshId)
					.build();
			response.responseItem(responseItem);
		}
		return new ResponseEntity<>(response.build(), HttpStatus.CREATED);
	}