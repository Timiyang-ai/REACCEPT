@Path("{role-id}")
    @DELETE
    @NoCache
    public void deleteRole(final @PathParam("role-id") String id) {
        RoleRepresentation rep = getRole(id);
        RoleModel role = getRoleModel(id);
        auth.requireManage();
        deleteRole(role);
        
        event.event(EventType.DELETE_ROLE)
            .representation(rep)
            .success();
    }