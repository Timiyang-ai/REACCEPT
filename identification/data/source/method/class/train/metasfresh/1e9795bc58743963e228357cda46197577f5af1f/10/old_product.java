@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved location"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("{bpartnerIdentifier}/location/{locationIdentifier}")
	@Override
	public ResponseEntity<JsonBPartnerLocation> retrieveBPartnerLocation(

			@ApiParam(value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifier,

			@ApiParam(value = LOCATION_IDENTIFIER_DOC) //
			@PathVariable("locationIdentifier") //
			@NonNull final String locationIdentifier)
	{
		final Optional<JsonBPartnerLocation> location = bPartnerEndpointservice.retrieveBPartnerLocation(bpartnerIdentifier, locationIdentifier);
		if (location.isPresent())
		{
			return ResponseEntity.ok(location.get());
		}
		return new ResponseEntity<JsonBPartnerLocation>(
				(JsonBPartnerLocation)null,
				HttpStatus.NOT_FOUND);
	}