  private <T extends Enum<T>> void assertAllDifferent(Class<T> clazz) throws Exception {
    Set<EnumSet<T>> allSets = new HashSet<>();

    int maxBits = 1 << clazz.getEnumConstants().length;
    for (int i = 0; i < maxBits; i++) {
      EnumSet<T> set = CollectionUtils.fromBits(i, clazz);
      int back = CollectionUtils.toBits(set);
      assertThat(i).isEqualTo(back); // Assert that a roundtrip is idempotent
      allSets.add(set);
    }

    assertThat(allSets).hasSize(maxBits); // Assert that every decoded value is different
  }