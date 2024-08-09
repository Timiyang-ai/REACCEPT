@Path("{role-id}")
    @GET
    @NoCache
    @Produces("application/json")
    public RoleRepresentation getRole(final @PathParam("role-id") String id) {
        RoleModel roleModel = getRoleModel(id);
        auth.requireView();
        
        event.event(EventType.VIEW_ROLE)
            .detail(Details.ROLE_ID, roleModel.getId())
            .detail(Details.ROLE_NAME, roleModel.getName())
            .success();
        
        return getRole(roleModel);
    }