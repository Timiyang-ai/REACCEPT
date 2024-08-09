@Path("{role-id}")
    @DELETE
    @NoCache
    public void deleteRole(final @PathParam("role-id") String id) {
        auth.requireManage();

        RoleModel role = getRoleModel(id);
        deleteRole(role);
        adminEvent.operation(OperationType.DELETE).resourcePath(uriInfo).success();
    }