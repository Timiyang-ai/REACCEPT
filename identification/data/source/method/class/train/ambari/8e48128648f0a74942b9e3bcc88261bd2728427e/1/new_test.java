@Test
  public void testIsHashCached() {
    assertFalse(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));
    String hash = m_hash.getHash(CLUSTERNAME, HOSTNAME);
    assertNotNull(hash);
    assertTrue(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));

    m_hash.invalidate(HOSTNAME);
    assertFalse(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));
    hash = m_hash.getHash(CLUSTERNAME, HOSTNAME);
    assertNotNull(hash);
    assertTrue(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));

    m_hash.invalidateAll();
    assertFalse(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));
    hash = m_hash.getHash(CLUSTERNAME, HOSTNAME);
    assertNotNull(hash);
    assertTrue(m_hash.isHashCached(CLUSTERNAME, HOSTNAME));
  }