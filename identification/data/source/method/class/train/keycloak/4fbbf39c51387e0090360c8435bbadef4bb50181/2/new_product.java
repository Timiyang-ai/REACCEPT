@Path("{role-id}")
    @PUT
    @Consumes("application/json")
    public void updateRole(final @PathParam("role-id") String id, final RoleRepresentation rep) {
        RoleModel role = getRoleModel(id);
        auth.requireManage();
        updateRole(rep, role);
        
        event.event(EventType.UPDATE_ROLE)
            .detail(Details.ROLE_ID, role.getId())
            .detail(Details.ROLE_NAME, role.getName())
            .success();
    }