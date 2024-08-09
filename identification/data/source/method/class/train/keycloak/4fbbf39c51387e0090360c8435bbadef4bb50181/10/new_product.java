@Path("{role-id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRole(final @PathParam("role-id") String id, final RoleRepresentation rep) {
        auth.requireManage();

        RoleModel role = getRoleModel(id);
        updateRole(rep, role);
        adminEvent.operation(OperationType.UPDATE).resourcePath(uriInfo).representation(rep).success();
    }