@Test
  public void testGetFramework() {
    when(requests.readFramework()).thenReturn(frameworkBean(framework()));

    client.getFramework();
    client.getFrameworkResourceBundle();

    verify(requests, times(1)).readFramework();
  }