@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated location"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@ApiOperation("Create or update a locations for a particular bpartner. If a location exists, then its properties that are *not* specified are left untouched.")
	@PutMapping("{bpartnerIdentifier}/location")
	@Override
	public ResponseEntity<JsonResponseUpsert> createOrUpdateLocation(

			@ApiParam(value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifier,

			@RequestBody @NonNull final JsonRequestLocationUpsert jsonLocation)
	{
		final JsonPersisterService persister = jsonServiceFactory.createPersister();
		final Optional<JsonResponseUpsert> jsonLocationId = persister.persistForBPartner(
				bpartnerIdentifier,
				jsonLocation,
				SyncAdvise.builder().ifExists(IfExists.UPDATE_MERGE).ifNotExists(IfNotExists.CREATE).build());

		if (!jsonLocationId.isPresent())
		{
			return new ResponseEntity<JsonResponseUpsert>(
					(JsonResponseUpsert)null,
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<JsonResponseUpsert>(
				jsonLocationId.get(),
				HttpStatus.CREATED);
	}