@Test
    public void testGetCoordinates() throws MalformedURLException, IOException {
        String expResult = "org/scala-lang/scala-library/2.11.2";
        String result = instance.getCoordinates();
        assertEquals(expResult, result);

        BintraySearch searcher = new BintraySearch(getSettings());
        InputStream stream = BaseTest.getResourceAsStream(this, "bintray/spring-core-3.0.0.RELEASE.json");
        BintrayArtifact[] ba = searcher.parseResponse(stream);

        expResult = "org/springframework/spring-core/3.0.0.RELEASE";
        result = ba[0].getCoordinates();
        assertEquals(expResult, result);

        expResult = "org/sonatype/aether/aether-util/1.7";
        stream = BaseTest.getResourceAsStream(this, "bintray/aether-util-1.7.json");
        ba = searcher.parseResponse(stream);
        result = ba[0].getCoordinates();
        assertEquals(expResult, result);
    }