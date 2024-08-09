@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved bpartner"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("{bpartnerIdentifier}")
	@Override
	public ResponseEntity<JsonResponseComposite> retrieveBPartner(

			@ApiParam(required = true, value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifierStr)
	{
		final IdentifierString bpartnerIdentifier = IdentifierString.of(bpartnerIdentifierStr);
		final Optional<JsonResponseComposite> result = bpartnerEndpointService.retrieveBPartner(bpartnerIdentifier);
		return okOrNotFound(result);
	}