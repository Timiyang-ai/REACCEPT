@GET
    @Path("/roles/{role}/permissions")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get permissions associated with a specific user role")
    @Timed
    public Response getRolePermissions(
            @PathParam("role")
            @ApiParam(defaultValue = DEFAULT_ROLE, value = EXAMPLE_ALL_ROLES)
            final String role) {
        try {
            return httpHeader.headers().entity(ImmutableMap.<String, Object>builder().put("permissions",
                    authorization.getPermissionsFromRole(toRole(role))).build()).build();
        } catch (Exception exception) {
            LOGGER.error("getRolePermissions failed for role={} with error:", role, exception);
            throw exception;
        }
    }