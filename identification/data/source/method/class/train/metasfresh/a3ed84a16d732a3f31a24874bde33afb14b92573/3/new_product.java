@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated bpartner(s)"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 422, message = "The request entity could not be processed")
	})
	@PutMapping
	@Override
	public ResponseEntity<JsonResponseUpsert> createOrUpdateBPartner(
			@RequestBody @NonNull final JsonRequestBPartnerUpsert bpartnerUpsertRequest)
	{
		final JsonPersisterService persister = jsonServiceFactory.createPersister();

		final SyncAdvise defaultSyncAdvise = bpartnerUpsertRequest.getSyncAdvise();

		final JsonResponseUpsertBuilder response = JsonResponseUpsert.builder();

		for (final JsonRequestBPartnerUpsertItem requestItem : bpartnerUpsertRequest.getRequestItems())
		{
			final BPartnerComposite syncToMetasfresh = persister.persist(
					IdentifierString.of(requestItem.getBpartnerIdentifier()),
					requestItem.getBpartnerComposite(),
					defaultSyncAdvise);

			final MetasfreshId metasfreshId = MetasfreshId.of(syncToMetasfresh.getBpartner().getId());

			final JsonResponseUpsertItem responseItem = JsonResponseUpsertItem.builder()
					.identifier(requestItem.getBpartnerIdentifier())
					.metasfreshId(metasfreshId)
					.build();
			response.responseItem(responseItem);
		}
		return new ResponseEntity<>(response.build(), HttpStatus.CREATED);
	}