@Test
  public void testToString() throws JSONException {
    Account account = Account.fromJson(refAccountJson);
    assertEquals("Account[" + account.getId() + "," + account.getSnapshotVersion() + "]", account.toString());
    for (int i = 0; i < CONTAINER_COUNT; i++) {
      Container container = Container.fromJson(containerJsonList.get(i), refAccountId);
      assertEquals("Container[" + account.getId() + ":" + container.getId() + "]", container.toString());
    }
  }