@GET
    @Path("metadataCacheDetails")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get assignments metadata cache details...")
    @Timed
    public Response getMetadataCacheDetails() {
        return httpHeader.headers().entity(assignments.metadataCacheDetails()).build();
    }