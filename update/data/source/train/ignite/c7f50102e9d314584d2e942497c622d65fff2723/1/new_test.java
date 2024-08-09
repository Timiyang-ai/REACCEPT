@Test
    public void testJoinWhileActivate1_WithCache_Server() throws Exception {
        if (MvccFeatureChecker.forcedMvcc())
            fail("https://issues.apache.org/jira/browse/IGNITE-10583");

        joinWhileActivate1(false, true);
    }