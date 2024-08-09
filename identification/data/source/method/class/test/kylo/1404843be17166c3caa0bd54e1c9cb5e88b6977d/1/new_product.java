@GET
    @Path("{table}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Fetches the status of a transformation.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the status of the transformation.", response = TransformResponse.class),
            @ApiResponse(code = 404, message = "The transformation does not exist.", response = TransformResponse.class),
            @ApiResponse(code = 500, message = "There was a problem accessing the data.", response = TransformResponse.class)
    })
    @Nonnull
    public Response getTable(@Nonnull @PathParam("table") final String id) {
        try {
            TransformResponse transformResponse = getTableTransformResponse(id);
            return Response.ok(transformResponse).build();
        } catch (final IllegalArgumentException e) {
            return error(Response.Status.NOT_FOUND, "getTable.notFound");
        } catch (final Exception e) {
            return error(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }