@Test
  public void testGetResources() throws Exception {
    System.out.println("\nStarting ClassPathLoaderTest#testGetResources");

    String resourceToGet = "org/apache/geode/internal/classpathloaderjunittest/DoesExist.class";
    Enumeration<URL> urls = ClassPathLoader.getLatest().getResources(resourceToGet);
    assertThat(urls).isNotNull();
    assertThat(urls.hasMoreElements()).isTrue();

    URL url = urls.nextElement();
    InputStream is = url != null ? url.openStream() : null;
    assertThat(is).isNotNull();

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
    assertThat(totalBytesRead).isEqualTo(GENERATED_CLASS_BYTES_COUNT);
  }