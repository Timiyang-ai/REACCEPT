@Test
  public void testGetOriginalImageUrl() throws NoSuchAlgorithmException {
    
    List <String> inputImageWikiUrls = Arrays.asList(
        "http://commons.wikimedia.org/wiki/Special:FilePath/Breviarium_Grimani_-_November.jpg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Flag_of_Moldova.svg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/POL_Kurów_COA.svg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Champs-Élysées_from_the_Arc_de_Triomphe.jpg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Corallushortulanus.png",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Aurélien_Clerc.jpg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Nyiragongo_1994.jpg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Torre_Belém_April_2009-4a.jpg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/De_fem_søstre_i_Århus_final_version.jpg",
        "http://commons.wikimedia.org/wiki/Special:FilePath/Network_of_the_Presidents_of_the_Supreme_Judicial_Courts_of_the_European_Union_Logo.png");
    
    List<String> outputOriginalUrls = Arrays.asList(
        "https://upload.wikimedia.org/wikipedia/commons/3/35/Breviarium_Grimani_-_November.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/2/27/Flag_of_Moldova.svg",
        "https://upload.wikimedia.org/wikipedia/commons/d/d4/POL_Kurów_COA.svg",
        "https://upload.wikimedia.org/wikipedia/commons/9/95/Champs-Élysées_from_the_Arc_de_Triomphe.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/2/2b/Corallushortulanus.png",
        "https://upload.wikimedia.org/wikipedia/commons/f/f7/Aurélien_Clerc.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/6/68/Nyiragongo_1994.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/6/65/Torre_Belém_April_2009-4a.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/2/23/De_fem_søstre_i_Århus_final_version.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/2/2e/Network_of_the_Presidents_of_the_Supreme_Judicial_Courts_of_the_European_Union_Logo.png");
    
    List<String> testOutputOriginalUrls = new LinkedList<>();    
    
    for(String url:inputImageWikiUrls) {
      testOutputOriginalUrls.add(WikidataImageExtractor.getOriginalImageUrl(url));
    }
    
    assertEquals(outputOriginalUrls.size(), testOutputOriginalUrls.size());
    assertArrayEquals(outputOriginalUrls.toArray(), testOutputOriginalUrls.toArray());
    
  }