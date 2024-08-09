    @Test
    public void higherThan() {
        String v1 = "1.1.0";
        String v2 = "1.1.0-beta";
        String v3 = "1.1.1";
        String v4 = "1.1.1-beta1";
        String v5 = "1.1.1-beta2";
        String v6 = "1.1.2-beta1";
        String v7 = "1.2.0";

        assertTrue(!VersionUtils.higherThan(v2, v1));
        assertTrue(VersionUtils.higherThan(v3, v2));
        assertTrue(VersionUtils.higherThan(v4, v3));
        assertTrue(VersionUtils.higherThan(v5, v4));
        assertTrue(VersionUtils.higherThan(v6, v5));
        assertTrue(VersionUtils.higherThan(v7, v1));
        assertTrue(VersionUtils.higherThan(v7, v2));
        assertTrue(VersionUtils.higherThan(v7, v6));


        assertTrue(VersionUtils.lowerThan(v2, v3));
        assertTrue(VersionUtils.lowerThan(v3, v4));
        assertTrue(VersionUtils.lowerThan(v4, v5));
        assertTrue(VersionUtils.lowerThan(v5, v6));
        assertTrue(VersionUtils.lowerThan(v1, v7));
        assertTrue(VersionUtils.lowerThan(v2, v7));
        assertTrue(VersionUtils.lowerThan(v6, v7));
        assertTrue(!VersionUtils.lowerThan(v2, v1));


        assertTrue(VersionUtils.equalsWith(v1, v2));
    }