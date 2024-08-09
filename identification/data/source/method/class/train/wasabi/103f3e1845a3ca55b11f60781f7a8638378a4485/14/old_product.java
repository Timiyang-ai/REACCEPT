@GET
    @Path("queueLength")
    @Produces(APPLICATION_JSON)
    public Response getAssignmentsQueueLength() {
        return httpHeader.headers().entity(assignments.queuesLength()).build();
    }