@Test
  public void testGetResources() throws Exception {
    System.out.println("\nStarting ClassPathLoaderTest#testGetResources");

    String resourceToGet = "org/apache/geode/internal/classpathloaderjunittest/DoesExist.class";
    Enumeration<URL> urls = ClassPathLoader.getLatest().getResources(resourceToGet);
    assertNotNull(urls);
    assertTrue(urls.hasMoreElements());

    URL url = urls.nextElement();
    InputStream is = url != null ? url.openStream() : null;
    assertNotNull(is);

    int totalBytesRead = 0;
    byte[] input = new byte[256];

    BufferedInputStream bis = new BufferedInputStream(is);
    for (int bytesRead = bis.read(input); bytesRead > -1;) {
      totalBytesRead += bytesRead;
      bytesRead = bis.read(input);
    }
    bis.close();

    // if the following fails then maybe javac changed and DoesExist.class
    // contains other than 374 bytes of data... consider updating this test
    assertEquals(GENERATED_CLASS_BYTES_COUNT, totalBytesRead);
  }