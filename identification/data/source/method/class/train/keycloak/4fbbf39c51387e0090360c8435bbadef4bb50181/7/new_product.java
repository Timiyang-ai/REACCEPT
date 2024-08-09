@Path("{role-id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRole(final @PathParam("role-id") String id, final RoleRepresentation rep) {
        auth.requireManage();

        RoleModel role = getRoleModel(id);
        updateRole(rep, role);

        if (role.isClientRole()) {
            adminEvent.resource(ResourceType.CLIENT_ROLE);
        } else {
            adminEvent.resource(ResourceType.REALM_ROLE);
        }

        adminEvent.operation(OperationType.UPDATE).resourcePath(uriInfo).representation(rep).success();
    }