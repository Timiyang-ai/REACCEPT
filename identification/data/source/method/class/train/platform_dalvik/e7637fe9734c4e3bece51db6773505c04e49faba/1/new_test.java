@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "implies",
        args = {java.security.ProtectionDomain.class, java.security.Permission.class}
    )
    public void test_implies() {
        Policy policy = Policy.getPolicy();
        char s = File.separatorChar;

        URL url = null;
        try {
            url = new URL("http://localhost");
        } catch (MalformedURLException ex) {
            throw new Error(ex);
        }
        CodeSource cs = new CodeSource(url,
                (java.security.cert.Certificate[]) null);

        FilePermission perm[] = new FilePermission[7];
        perm[0] = new FilePermission("test1.file", "write");
        perm[1] = new FilePermission("test1.file",
                "read, write, execute,delete");
        perm[2] = new FilePermission(s + "tmp" + s + "test" + s + "*",
                "read,write");
        perm[3] = new FilePermission(s + "tmp" + s + "test" + s
                + "collection.file", "read");
        perm[4] = new FilePermission(s + "windows" + "*", "delete");
        perm[5] = new FilePermission("aFile.file", "read");
        perm[6] = new FilePermission("hello.file", "write");
        Permissions perms = new Permissions();
        for (int i = 0; i < perm.length; i++) {
            perms.add(perm[i]);
        }
        ProtectionDomain pd = new ProtectionDomain(cs, perms);

        assertTrue(policy.implies(pd, perm[0]));
        assertTrue(policy.implies(pd, perm[1]));
        assertTrue(policy.implies(pd, perm[2]));
        assertTrue(policy.implies(pd, perm[3]));
        assertTrue(policy.implies(pd, perm[4]));
        assertTrue(policy.implies(pd, perm[5]));
        assertTrue(policy.implies(pd, perm[6]));
        assertTrue(policy.implies(pd,
                new FilePermission("test1.file", "delete")));
        assertTrue(policy.implies(pd,
                new FilePermission(s + "tmp" + s + "test" + s + "test1.file", "read")));
        
        assertFalse(policy.implies(pd, new FilePermission("aFile.file",
                "delete")));
        assertFalse(policy.implies(pd, new FilePermission("hello.file",
                "delete")));
        assertFalse(policy.implies(pd, new FilePermission(s + "tmp" + s
                + "test" + s + "collection.file", "delete")));
        assertFalse(policy.implies(pd, new FilePermission(s + "tmp" + s
                + "test" + s + "*", "delete")));
        assertFalse(policy.implies(pd, new FilePermission("hello.file",
                "execute")));

        try {
            assertFalse(policy.implies(pd, null));
        } catch (NullPointerException e) {
            // expected
        }
        
        try {
            assertFalse(policy.implies(null, new FilePermission("test1.file", "delete")));
        } catch (NullPointerException e) {
            fail("Unexpected NullPointerException");
        }
        
        try {
            assertFalse(policy.implies(null, null));
        } catch (NullPointerException e) {
            // ok
        }
    }