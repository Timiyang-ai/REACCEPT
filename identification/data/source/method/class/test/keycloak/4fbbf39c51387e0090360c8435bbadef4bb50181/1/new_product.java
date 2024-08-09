@Path("{role-id}")
    @PUT
    @Consumes("application/json")
    public void updateRole(final @PathParam("role-id") String id, final RoleRepresentation rep) {
        RoleModel role = getRoleModel(id);
        auth.requireManage();
        updateRole(rep, role);
    }