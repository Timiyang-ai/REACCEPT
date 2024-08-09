@Test
    public void testReadAll_IT() throws Exception {
        // Given
        assertFF4J.assertThatStoreHasSize(5);
       final Feature f1 = ff4j.getFeature(F1);
        /*
         * ACCESS ON FF4J WebResource wRes = resource().path(APIPATH); ClientResponse httpRes = wRes.get(ClientResponse.class);
         * System.out.println(String.valueOf(httpRes.getEntity(String.class)));
         * 
         * // ACCESS ON STORE WebResource wRes2 = wRes.path("features"); ClientResponse httpRes2 =
         * wRes2.get(ClientResponse.class); System.out.println(String.valueOf(httpRes2.getEntity(String.class)));
         */

        final ResourceConfig rc = new PackagesResourceConfig("org.ff4j");
        final Map<String, Object> config = new HashMap<String, Object>();
        config.put("com.sun.jersey.api.json.POJOMappingFeature", true);
        rc.setPropertiesAndFeatures(config);

        // GET FEATURE
        WebResource wRes3 = resource().path(APIPATH).path("features").path("first");
        ClientResponse httpRes3 = wRes3.get(ClientResponse.class);
        System.out.println(String.valueOf(httpRes3.getEntity(String.class)));

        WebResource wRes0 = resource().path(APIPATH).path("features").path("tralala").path("enable");
        ClientResponse httpRes0 = wRes0.post(ClientResponse.class);
        // System.out.println(String.valueOf(httpRes0.getEntity(String.class)));
        System.out.println(String.valueOf(httpRes0.getStatus()));

        // DELETE FEATURE
        ClientResponse httpRes4 = wRes3.delete(ClientResponse.class);
        System.out.println(String.valueOf(httpRes4.getStatus()));

        // ANOTHER 404
        ClientResponse httpRes5 = wRes3.get(ClientResponse.class);
        System.out.println(String.valueOf(httpRes5.getStatus()));

        // PUT
        wRes3.put(f1.toString().getBytes());


    }