@GET
    @Path("queueDetails")
    @Produces(APPLICATION_JSON)
    public Response getAssignmentsQueueDetails() {
        try {
            return httpHeader.headers().entity(assignments.queuesDetails()).build();
        } catch (Exception exception) {
            LOGGER.error("getAssignmentsQueueDetails failed with error:", exception);
            throw exception;
        }
    }