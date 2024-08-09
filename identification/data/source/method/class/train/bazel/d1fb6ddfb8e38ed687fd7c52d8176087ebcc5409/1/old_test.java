  @Test
  public void getPythonVersion_FallBackOnDefaultPythonVersion() throws Exception {
    // Run it twice with two different values for the incompatible flag to confirm it's actually
    // reading getDefaultPythonVersion() and not some other source of default values. Note that
    // --incompatible_py3_is_default requires --incompatible_allow_python_version_transitions.
    PythonOptions py2Opts =
        parsePythonOptions(
            "--incompatible_allow_python_version_transitions=true",
            "--incompatible_py3_is_default=false");
    PythonOptions py3Opts =
        parsePythonOptions(
            "--incompatible_allow_python_version_transitions=true",
            "--incompatible_py3_is_default=true");
    assertThat(py2Opts.getPythonVersion()).isEqualTo(PythonVersion.PY2);
    assertThat(py3Opts.getPythonVersion()).isEqualTo(PythonVersion.PY3);
  }