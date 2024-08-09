@Ignore
    @Test
    public void testUploadProvJsonForBundle() throws Exception {
        System.out.println("uploadProvJsonForBundle");
        // FIXME: Support sending standard javax.json.JsonObject (JSON-P, JRS 353) rather than org.json.JSONObject.
        JSONObject provJson = null;
        boolean expResult = false;
        long bundleId = provenanceRestServiceBean.createEmptyBundleFromName("weAreGoingToUploadSomeJson");
        String bundleName = "huh? Shouldn't we be sending an id?";
        boolean result = provenanceRestServiceBean.uploadProvJsonForBundle(provJson, bundleName);
    }