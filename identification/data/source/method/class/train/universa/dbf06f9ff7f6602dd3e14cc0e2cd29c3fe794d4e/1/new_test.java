    @Test
    public void equals() throws Exception {
        RoleLink r1 = new RoleLink("name", "target");
        RoleLink r2 = new RoleLink("name", "target1");
        RoleLink r3 = new RoleLink("name1", "target");
        assertTrue(r1.equalsIgnoreName(r3));
        assertFalse(r1.equalsIgnoreName(r2));
        assertFalse(r2.equalsIgnoreName(r3));
        assertNotEquals(r1, r3);
        assertNotEquals(r1, r2);
        assertNotEquals(r2, r3);
    }