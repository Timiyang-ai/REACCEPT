@Test
    public void testUploadProvJsonForBundle() throws UnirestException {
        System.out.println("uploadProvJsonForBundle");
        JsonObjectBuilder innerJson = Json.createObjectBuilder();
        innerJson.add("entity", Json.createObjectBuilder()
                .add("bbc:news/science-environment-17526723", Json.createObjectBuilder()
                        .add("prov:type", "a news item for desktop"))
                .add("bbc:news/mobile/science-environment-17526723", Json.createObjectBuilder()
                        .add("prov:type", "a news item for mobile devices"))
        );
        innerJson.add("alternateOf", Json.createObjectBuilder()
                .add("_:aO1", Json.createObjectBuilder()
                        .add("prov:alternate2", "bbc:news/science-environment-17526723")
                        .add("prov:alternate1", "bbc:news/mobile/science-environment-17526723")
                )
        );
        JsonObject provJson = innerJson.build();
        String uuid = UUID.randomUUID().toString();
        long bundleId = provenanceRestServiceBean.createEmptyBundleFromName(uuid);
        System.out.println("uploading JSON for bundle id " + bundleId);
        // TODO: What would a better bundle name be?
        String bundleName = bundleId + "-uploadJson";
        JsonObject result = provenanceRestServiceBean.uploadProvJsonForBundle(provJson, bundleName);
        System.out.println("result: " + result);
        System.out.println("result: " + JsonUtil.prettyPrint(result.toString()));
        long idReturned = result.getJsonNumber("id").longValue();
        System.out.println("id returned: " + idReturned);
        assertTrue(idReturned > 0 && idReturned <= Long.MAX_VALUE);
        // Is the id returned (i.e. 51) always one greater than the bundle id (i.e. 50)?
        assertEquals(idReturned, bundleId + 1);
    }