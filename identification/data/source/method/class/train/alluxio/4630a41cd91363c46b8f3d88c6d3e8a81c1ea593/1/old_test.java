  @Test
  public void keySet() {
    Set<PropertyKey> expected = new HashSet<>(PropertyKey.defaultKeys());
    assertThat(mProperties.keySet(), is(expected));
    PropertyKey newKey = new PropertyKey.Builder("keySetNew").build();
    mProperties.put(newKey, "value", Source.RUNTIME);
    expected.add(newKey);
    assertThat(mProperties.keySet(), is(expected));
  }