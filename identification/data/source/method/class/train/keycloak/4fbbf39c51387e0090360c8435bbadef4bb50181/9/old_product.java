@Path("{role-id}")
    @GET
    @NoCache
    @Produces(MediaType.APPLICATION_JSON)
    public RoleRepresentation getRole(final @PathParam("role-id") String id) {
        auth.requireAny();

        RoleModel roleModel = getRoleModel(id);
        return getRole(roleModel);
    }