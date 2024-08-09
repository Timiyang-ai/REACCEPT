@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{deviceId}/relinquish")
    public Response relinquishMastership(@PathParam("deviceId") String deviceId) {
        DeviceId id = DeviceId.deviceId(deviceId);

        // TODO: will not use CompletableFuture when MastershipService
        // provides a non CompletableFuture object as an output
        CompletableFuture<Void> result =
                nullIsNotFound(mastershipService.relinquishMastership(id), RESULT_NOT_FOUND);

        try {
            result.get();
            return Response.created(id.uri()).build();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalArgumentException(e);
        }
    }