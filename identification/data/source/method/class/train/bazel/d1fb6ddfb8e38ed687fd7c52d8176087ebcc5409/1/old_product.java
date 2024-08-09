public PythonVersion getPythonVersion(PythonVersion attributeVersion) {
    return ignorePythonVersionAttribute || attributeVersion == null
        ? defaultPythonVersion
        : attributeVersion;
  }