@GET
    @Path("queueDetails")
    @Produces(APPLICATION_JSON)
    public Response getAssignmentsQueueDetails() {
        return httpHeader.headers().entity(assignments.queuesDetails()).build();
    }