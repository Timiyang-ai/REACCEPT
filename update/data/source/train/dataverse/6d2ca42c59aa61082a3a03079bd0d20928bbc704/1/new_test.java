@Test
    public void testGetDisplayNameFromDiscoFeed() {
//        System.out.println("getDisplayNameFromDiscoFeed");

        String discoFeedExample = null;
        try {
            discoFeedExample = new String(Files.readAllBytes(Paths.get("src/main/webapp/resources/dev/sample-shib-identities.json")));
        } catch (IOException ex) {
            Logger.getLogger(ShibUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShibUtil shibUtil = new ShibUtil();

        String testShib = shibUtil.getDisplayNameFromDiscoFeed("https://idp.testshib.org/idp/shibboleth", discoFeedExample);
        assertEquals("TestShib Test IdP", testShib);

        String harvardStage = shibUtil.getDisplayNameFromDiscoFeed("https://stage.fed.huit.harvard.edu/idp/shibboleth", discoFeedExample);
        assertEquals("Harvard Test IdP", harvardStage);

        String minimal = shibUtil.getDisplayNameFromDiscoFeed("https://minimal.com/shibboleth", discoFeedExample);
        assertEquals(null, minimal);

        String unknown = shibUtil.getDisplayNameFromDiscoFeed("https://nosuchdomain.com/idp/shibboleth", discoFeedExample);
        assertEquals(null, unknown);

        String searchForNull = shibUtil.getDisplayNameFromDiscoFeed(null, discoFeedExample);
        assertEquals(null, searchForNull);

        /**
         * @todo Is it an error to pass a null discoFeed?
         */
//        String nullDiscoFeed = shibUtil.getDisplayNameFromDiscoFeed("https://idp.testshib.org/idp/shibboleth", null);
//        assertEquals(null, nullDiscoFeed);
        /**
         * @todo Is it an error to pass junk (non-JSON) as a discoFeed?
         */
//        String unparseAbleDiscoFeed = shibUtil.getDisplayNameFromDiscoFeed("https://idp.testshib.org/idp/shibboleth", "unparseAbleAsJson");
//        assertEquals(null, unparseAbleDiscoFeed);
    }