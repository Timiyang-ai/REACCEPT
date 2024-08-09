  @Test
  public void matches() {
    assertTrue(MediaType.matches("application/json", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));

    assertTrue(MediaType.matches("application/json", "text/html, */*"));

    assertTrue(MediaType.matches("application/json", "application/json"));

    assertTrue(MediaType.matches("application/*+json", "application/xml, application/bar+json "));

    assertFalse(MediaType.matches("application/json", "text/plain"));

    assertFalse(MediaType.matches("application/json", "text/plain"));

    assertTrue(MediaType.matches("application/json", "*"));
    assertTrue(MediaType.matches("application/json", "*/*"));

    assertFalse(MediaType.matches("application/json", "application/jsonx"));
    assertFalse(MediaType.matches("application/json", "application/xjson"));

    // wild
    assertTrue(MediaType.matches("application/*json", "application/json"));
    assertTrue(MediaType.matches("application/*+json", "application/foo+json"));

    assertTrue(MediaType.matches("application/*json", "application/foojson"));
    assertFalse(MediaType.matches("application/*+json", "application/foojson"));
    assertFalse(MediaType.matches("application/*+json", "text/plain"));
    assertFalse(MediaType.matches("application/*+json", "application/jsonplain"));

    // accept header
    assertTrue(MediaType.matches("application/json", "application/json, application/xml"));

    assertTrue(MediaType.matches("application/json", "application/xml, application/json"));

    assertTrue(MediaType.matches("application/*+json", "application/xml, application/bar+json"));

    assertTrue(MediaType.matches("application/json", "application/json, application/xml"));
  }