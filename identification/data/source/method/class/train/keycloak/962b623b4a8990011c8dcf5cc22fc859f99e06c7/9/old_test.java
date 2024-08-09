    @Test
    public void deleteRole() {
        assertNotNull(resource.getRole(ids.get("role-a")));
        resource.deleteRole(ids.get("role-a"));
        assertAdminEvents.assertEvent(realmId, OperationType.DELETE, AdminEventPaths.roleByIdResourcePath(ids.get("role-a")), ResourceType.REALM_ROLE);

        try {
            resource.getRole(ids.get("role-a"));
            fail("Expected 404");
        } catch (NotFoundException e) {
        }
    }