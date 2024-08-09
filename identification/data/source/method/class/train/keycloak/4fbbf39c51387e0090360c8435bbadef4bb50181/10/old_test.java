    @Test
    public void updateRole() {
        RoleRepresentation role = resource.getRole(ids.get("role-a"));

        role.setName("role-a-new");
        role.setDescription("Role A New");

        resource.updateRole(ids.get("role-a"), role);
        assertAdminEvents.assertEvent(realmId, OperationType.UPDATE, AdminEventPaths.roleByIdResourcePath(ids.get("role-a")), role, ResourceType.REALM_ROLE);

        role = resource.getRole(ids.get("role-a"));

        assertNotNull(role);
        assertEquals("role-a-new", role.getName());
        assertEquals("Role A New", role.getDescription());
        assertFalse(role.isComposite());
    }