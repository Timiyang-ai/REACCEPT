@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "equals",
        args = {java.lang.Object.class}
    )
    public void test_equals() {
        final Permission perm1 = new PropertyPermission("java.class.path",
                "read");
        final Permission perm2 = new PropertyPermission("java.path", "write");

        PermissionCollection col1 = perm1.newPermissionCollection();
        col1.add(perm1);
        final ProtectionDomain pd1 = new ProtectionDomain(null, col1);
        AccessControlContext acc1 = new AccessControlContext(
                new ProtectionDomain[] { pd1 });

        AccessControlContext acc2 = new AccessControlContext(
                new ProtectionDomain[] { pd1 });

        PermissionCollection col2 = perm2.newPermissionCollection();
        col2.add(perm2);
        col2.add(perm2);
        final ProtectionDomain pd2 = new ProtectionDomain(null, col2);
        AccessControlContext acc3 = new AccessControlContext(
                new ProtectionDomain[] { pd2 });

        assertFalse(acc1.equals(null));
        assertFalse(acc2.equals(null));
        assertFalse(acc3.equals(null));

        assertTrue(acc1.equals(acc2));
        assertTrue(acc2.equals(acc1));
        assertFalse(acc1.equals(acc3));
        assertFalse(acc2.equals(acc3));

        AccessControlContext context = AccessController.getContext();

        AccessControlContext acc4 = new AccessControlContext(context, null);
        AccessControlContext acc5 = new AccessControlContext(context,
                new SubjectDomainCombiner(new Subject()));

        AccessControlContext acc6 = new AccessControlContext(context, null);

        assertFalse(acc4.equals(null));
        assertFalse(acc5.equals(null));

        assertFalse(acc4.equals(acc5));
        assertFalse(acc5.equals(acc4));

        assertTrue(acc4.equals(acc6));
        assertTrue(acc6.equals(acc4));
    }