@GET
    @Path("/{id}/commands")
    @ApiOperation(
            value = "Get the commands this application is associated with",
            notes = "Get the commands which this application supports.",
            response = Command.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_NOT_FOUND,
                    message = "Application not found"
            ),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_PRECON_FAILED,
                    message = "Invalid ID supplied"
            ),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "Genie Server Error due to Unknown Exception"
            )
    })
    public Set<Command> getCommandsForApplication(
            @ApiParam(
                    value = "Id of the application to get the commands for.",
                    required = true
            )
            @PathParam("id")
            final String id
    ) throws GenieException {
        LOG.info("Called with id " + id);
        return this.applicationConfigService.getCommandsForApplication(id);
    }