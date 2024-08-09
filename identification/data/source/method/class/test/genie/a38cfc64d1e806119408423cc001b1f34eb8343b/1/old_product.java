@GET
    @Path("/{id}/commands")
    @ApiOperation(
            value = "Get the commands for a cluster",
            notes = "Get the commands for the cluster with the supplied id.",
            response = Command.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_NOT_FOUND,
                    message = "Cluster not found"
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
    public List<Command> getCommandsForCluster(
            @ApiParam(
                    value = "Id of the cluster to get commands for.",
                    required = true
            )
            @PathParam("id")
            final String id
    ) throws GenieException {
        LOG.info("Called with id " + id);
        return this.clusterConfigService.getCommandsForCluster(id);
    }