@Test
  public void testInvalidateHosts() {
    assertFalse(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));
    String hash = m_hash.getHash(CLUSTERNAME, HOSTNAME);
    assertNotNull(hash);
    assertTrue(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));

    Set<String> invalidatedHosts = m_hash.invalidateHosts(m_hdfsHost);
    assertFalse(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));
    assertNotNull(invalidatedHosts);
    assertEquals(1, invalidatedHosts.size());
    assertTrue(invalidatedHosts.contains(HOSTNAME));
  }