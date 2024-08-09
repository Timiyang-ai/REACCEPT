    @Test
    public void getRole() {
        RoleRepresentation role = resource.getRole(ids.get("role-a"));
        assertNotNull(role);
        assertEquals("role-a", role.getName());
        assertEquals("Role A", role.getDescription());
        assertFalse(role.isComposite());
    }