    @Test
    public void getRolePermissions() throws Exception {

        AuthorizationResource authorizationResource = new AuthorizationResource(authorization, new HttpHeader("jaba-???", "600"));

        HashMap<String, Object> result = new HashMap<>();

        when(authorization.getPermissionsFromRole(Role.READONLY)).thenReturn(Role.READONLY.getRolePermissions());
        result.put("permissions", Role.READONLY.getRolePermissions());
        Response response = authorizationResource.getRolePermissions(Role.READONLY.toString());
        assertThat(response.getEntity(), CoreMatchers.<Object>equalTo(result));
    }