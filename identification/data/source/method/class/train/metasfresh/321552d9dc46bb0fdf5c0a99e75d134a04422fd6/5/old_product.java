@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully created or updated location"),
			@ApiResponse(code = 401, message = "You are not authorized to create or update the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	})
	@PostMapping("{bpartnerIdentifier}/location")
	@Override
	public ResponseEntity<JsonUpsertResponseItem> createOrUpdateLocation(

			@ApiParam(value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifier,

			@RequestBody @NonNull final JsonBPartnerLocation jsonLocation)
	{
		final JsonPersisterService persister = jsonServiceFactory.createPersister();
		final Optional<MetasfreshId> jsonLocationId = persister.persist(bpartnerIdentifier, jsonLocation);

		if (!jsonLocationId.isPresent())
		{
			return new ResponseEntity<JsonUpsertResponseItem>(
					(JsonUpsertResponseItem)null,
					HttpStatus.NOT_FOUND);
		}

		final JsonUpsertResponseItem result = JsonUpsertResponseItem.builder()
				.externalId(jsonLocation.getExternalId())
				.metasfreshId(jsonLocationId.get())
				.build();
		return new ResponseEntity<JsonUpsertResponseItem>(
				result,
				HttpStatus.CREATED);
	}