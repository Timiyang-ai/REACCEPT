@Path("{role-id}")
    @GET
    @NoCache
    @Produces("application/json")
    public RoleRepresentation getRole(final @PathParam("role-id") String id) {
        RoleModel roleModel = getRoleModel(id);
        auth.requireView();

        return getRole(roleModel);
    }