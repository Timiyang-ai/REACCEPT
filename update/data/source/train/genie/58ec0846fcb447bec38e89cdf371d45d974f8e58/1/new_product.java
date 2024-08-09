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
    public List<Command> getCommandsForApplication(
            @ApiParam(
                    value = "Id of the application to get the commands for.",
                    required = true
            )
            @PathParam("id")
            final String id,
            @ApiParam(
                    value = "The statuses of the commands to find.",
                    allowableValues = "ACTIVE, DEPRECATED, INACTIVE"
            )
            @QueryParam("status")
            final Set<String> statuses
    ) throws GenieException {
        LOG.info("Called with id " + id);

        Set<CommandStatus> enumStatuses = null;
        if (!statuses.isEmpty()) {
            enumStatuses = EnumSet.noneOf(CommandStatus.class);
            for (final String status : statuses) {
                if (StringUtils.isNotBlank(status)) {
                    enumStatuses.add(CommandStatus.parse(status));
                }
            }
        }
        return this.applicationConfigService.getCommandsForApplication(id, enumStatuses);
    }