@PUT
    @Path("/roles")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update roles for a list of users and applications")
    @Timed
    public Response updateUserRoles(@ApiParam(name = "userRoleList", value = "Please see model example", required = true)
                                    final UserRoleList userRoleList,

                                    @HeaderParam(AUTHORIZATION)
                                    @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                    final String authorizationHeader) {
        List<Map> statuses = updateUserRole(userRoleList, authorizationHeader);

        return httpHeader.headers()
                .entity(ImmutableMap.<String, Object>builder().put("assignmentStatuses", statuses).build()).build();
    }