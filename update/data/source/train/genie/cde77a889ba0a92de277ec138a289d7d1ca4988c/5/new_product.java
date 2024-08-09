@DELETE
    @Path("/{id}/tags/{tag}")
    @ApiOperation(
            value = "Remove a tag from a command",
            notes = "Remove the given tag from the command with given id.  Note that the genie name space tags"
                    + "prefixed with genie.id and genie.name cannot be deleted.",
            response = String.class,
            responseContainer = "Set"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_NOT_FOUND,
                    message = "Command not found"
            ),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_PRECON_FAILED,
                    message = "Invalid required parameter supplied"
            ),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "Genie Server Error due to Unknown Exception"
            )
    })
    public Set<String> removeTagForCommand(
            @ApiParam(
                    value = "Id of the command to delete from.",
                    required = true
            )
            @PathParam("id")
            final String id,
            @ApiParam(
                    value = "The tag to remove.",
                    required = true
            )
            @PathParam("tag")
            final String tag
    ) throws GenieException {
        LOG.info("Called with id " + id + " and tag " + tag);
        return this.commandConfigService.removeTagForCommand(id, tag);
    }