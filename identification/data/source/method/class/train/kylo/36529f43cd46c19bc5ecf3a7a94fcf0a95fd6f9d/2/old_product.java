@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Queries a Hive table and applies a series of transformations on the rows.")
    @ApiResponses({
                      @ApiResponse(code = 200, message = "Returns the status of the transformation.", response = TransformResponse.class),
                      @ApiResponse(code = 400, message = "The request could not be parsed.", response = TransformResponse.class),
                      @ApiResponse(code = 500, message = "There was a problem processing the data.", response = TransformResponse.class)
                  })
    @Nonnull
    public Response create(@ApiParam(value = "The request indicates the transformations to apply to the source table and how the user wishes the results to be displayed. Exactly one parent or source"
                                             + " must be specified.", required = true)
                           @Nullable final TransformRequest request) {
        // Validate request
        if (request == null || request.getScript() == null) {
            return error(Response.Status.BAD_REQUEST, "transform.missingScript");
        }
        if (request.getParent() != null) {
            if (request.getParent().getScript() == null) {
                return error(Response.Status.BAD_REQUEST, "transform.missingParentScript");
            }
            if (request.getParent().getTable() == null) {
                return error(Response.Status.BAD_REQUEST, "transform.missingParentTable");
            }
        }

        // Execute request
        try {
            TransformResponse response = this.transformService.execute(request);
            return Response.ok(response).build();
        } catch (ScriptException e) {
            return error(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }