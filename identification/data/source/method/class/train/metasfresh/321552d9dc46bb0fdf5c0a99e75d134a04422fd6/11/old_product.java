@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated location"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@ApiOperation("Create of update a location for a particular bpartner. If the location exists, then the properties that are *not* specified are left untouched.")
	@PutMapping("{bpartnerIdentifier}/location")
	@Override
	public ResponseEntity<JsonResponseUpsertItem> createOrUpdateLocation(

			@ApiParam(value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifier,

			@RequestBody @NonNull final JsonRequestLocation jsonLocation)
	{
		final JsonPersisterService persister = jsonServiceFactory.createPersister();
		final Optional<MetasfreshId> jsonLocationId = persister.persist(
				bpartnerIdentifier,
				jsonLocation,
				SyncAdvise.builder().ifExists(IfExists.UPDATE_MERGE).ifNotExists(IfNotExists.CREATE).build());

		if (!jsonLocationId.isPresent())
		{
			return new ResponseEntity<JsonResponseUpsertItem>(
					(JsonResponseUpsertItem)null,
					HttpStatus.NOT_FOUND);
		}

		final JsonResponseUpsertItem result = JsonResponseUpsertItem.builder()
				.externalId(jsonLocation.getExternalId())
				.metasfreshId(jsonLocationId.get())
				.build();
		return new ResponseEntity<JsonResponseUpsertItem>(
				result,
				HttpStatus.CREATED);
	}