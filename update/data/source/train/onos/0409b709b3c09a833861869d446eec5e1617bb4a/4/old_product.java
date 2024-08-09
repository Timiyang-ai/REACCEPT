@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{deviceId}/request")
    public Response requestRoleFor(@PathParam("deviceId") String deviceId) {

        // TODO: will not use CompletableFuture when MastershipService
        // provides a non CompletableFuture object as an output
        CompletableFuture<MastershipRole> result =
                nullIsNotFound(mastershipService.requestRoleFor(
                        DeviceId.deviceId(deviceId)), MASTERSHIP_ROLE_NOT_FOUND);

        try {
            MastershipRole role = result.get();
            ObjectNode root = codec(MastershipRole.class).encode(role, this);
            return ok(root).build();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalArgumentException(e);
        }
    }