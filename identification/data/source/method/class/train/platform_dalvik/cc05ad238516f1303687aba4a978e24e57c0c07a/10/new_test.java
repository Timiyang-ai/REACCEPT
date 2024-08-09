@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "equals",
        args = {java.lang.Object.class}
    )
    public void testEquals()
    {
        BasicPermission b1 = new BasicPermissionImpl("abc");
        BasicPermission b2 = null;
        assertTrue(b1.equals(b1)); 
        assertFalse(b1.equals(null));
        assertFalse(b1.equals(new Object()));
        assertFalse(b1.equals("abc"));
        assertTrue(b1.equals(b2 = new BasicPermissionImpl("abc")));
        assertTrue(b1.hashCode() == b2.hashCode());
        assertFalse(b1.equals(new BasicPermission("abc"){}));
        assertFalse(b1.equals(new BasicPermissionImpl("abc.*")));
    }