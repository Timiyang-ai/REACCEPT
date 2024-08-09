  @Test
  public void parseSrcsValue() {
    assertThat(PythonVersion.parseSrcsValue("PY2")).isEqualTo(PythonVersion.PY2);
    assertThat(PythonVersion.parseSrcsValue("PY3")).isEqualTo(PythonVersion.PY3);
    assertThat(PythonVersion.parseSrcsValue("PY2AND3")).isEqualTo(PythonVersion.PY2AND3);
    assertThat(PythonVersion.parseSrcsValue("PY2ONLY")).isEqualTo(PythonVersion.PY2ONLY);
    assertThat(PythonVersion.parseSrcsValue("PY3ONLY")).isEqualTo(PythonVersion.PY3ONLY);
    assertIsInvalidForParseSrcsValue("_INTERNAL_SENTINEL");
    assertIsInvalidForParseSrcsValue("not an enum value");
  }