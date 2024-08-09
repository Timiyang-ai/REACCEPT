@Path("{role-id}")
    @DELETE
    @NoCache
    public void deleteRole(final @PathParam("role-id") String id) {
        RoleModel role = getRoleModel(id);
        auth.requireManage();
        deleteRole(role);
        
        event.event(EventType.DELETE_ROLE)
            .detail(Details.ROLE_ID, role.getId())
            .detail(Details.ROLE_NAME, role.getName())
            .success();
    }