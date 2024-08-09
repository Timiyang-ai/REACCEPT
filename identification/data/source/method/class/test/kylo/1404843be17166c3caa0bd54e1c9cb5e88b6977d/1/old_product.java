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
            TransformJob job = transformService.getTransformJob(id);

            if (job.isDone()) {
                return Response.ok(job.get()).build();
            } else {
                TransformResponse response = new TransformResponse();
                response.setProgress(job.progress());
                response.setStatus(TransformResponse.Status.PENDING);
                response.setTable(job.getGroupId());
                return Response.ok(response).build();
            }
        } catch (final IllegalArgumentException e) {
            return error(Response.Status.NOT_FOUND, "getTable.notFound");
        } catch (final Exception e) {
            return error(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }