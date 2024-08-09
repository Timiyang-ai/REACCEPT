@POST
    @Path("unlock-and-freeze")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @NonIdempotent boolean unlockAndFreeze(HeldLocksToken token);