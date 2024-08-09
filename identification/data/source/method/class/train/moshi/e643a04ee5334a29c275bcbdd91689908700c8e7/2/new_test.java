  @Test public void arrayOf() {
    assertThat(Types.getRawType(Types.arrayOf(int.class))).isEqualTo(int[].class);
    assertThat(Types.getRawType(Types.arrayOf(List.class))).isEqualTo(List[].class);
    assertThat(Types.getRawType(Types.arrayOf(String[].class))).isEqualTo(String[][].class);
  }