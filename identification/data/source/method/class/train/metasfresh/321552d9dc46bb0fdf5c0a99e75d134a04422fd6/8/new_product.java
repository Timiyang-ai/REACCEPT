@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated location"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 422, message = "The request entity could not be processed")
	})
	@ApiOperation("Create or update a locations for a particular bpartner. If a location exists, then its properties that are *not* specified are left untouched.")
	@PutMapping("{bpartnerIdentifier}/location")
	@Override
	public ResponseEntity<JsonResponseUpsert> createOrUpdateLocation(

			@ApiParam(required = true, value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifierStr,

			@RequestBody @NonNull final JsonRequestLocationUpsert jsonLocation)
	{
		final IdentifierString bpartnerIdentifier = IdentifierString.of(bpartnerIdentifierStr);

		final JsonPersisterService persister = jsonServiceFactory.createPersister();
		final Optional<JsonResponseUpsert> jsonLocationId = persister.persistForBPartner(
				bpartnerIdentifier,
				jsonLocation,
				SyncAdvise.builder().ifExists(IfExists.UPDATE_MERGE).ifNotExists(IfNotExists.CREATE).build());

		return createdOrNotFound(jsonLocationId);
	}