@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved bpartner"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("{bpartnerIdentifier}")
	@Override
	public ResponseEntity<JsonBPartnerComposite> retrieveBPartner(

			@ApiParam(value = BPARTER_IDENTIFIER_DOC) //
			@PathVariable("bpartnerIdentifier") //
			@NonNull final String bpartnerIdentifier)
	{
		final JsonBPartnerComposite result = MockDataUtil.createMockBPartnerComposite(bpartnerIdentifier);
		return ResponseEntity.ok(result);
	}