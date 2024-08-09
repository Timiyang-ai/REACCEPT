@Test
  public void testInvalidateHosts() {
    assertFalse(m_hash.isHashCached(HOSTNAME));
    String hash = m_hash.getHash(CLUSTERNAME, HOSTNAME);
    assertNotNull(hash);
    assertTrue(m_hash.isHashCached(HOSTNAME));

    m_hash.invalidateHosts(m_hdfsHost);
    assertFalse(m_hash.isHashCached(HOSTNAME));
  }