@GET
    @Path("queueLength")
    @Produces(APPLICATION_JSON)
    public Response getAssignmentsQueueLength() {
        try {
            return httpHeader.headers().entity(assignments.queuesLength()).build();
        } catch (Exception exception) {
            LOGGER.error("getAssignmentsQueueLength failed with error:", exception);
            throw exception;
        }
    }