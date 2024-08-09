  @Test
  public void getDeployedFileName() throws Exception {
    // deployed file name should always have .v# appended to it
    createJarFile("test.jar");
    assertThatThrownBy(() -> new DeployedJar(jarFile).getDeployedFileName())
        .isInstanceOf(IllegalStateException.class);

    createJarFile("test.v1.jar");
    assertThat(new DeployedJar(jarFile).getDeployedFileName()).isEqualTo("test.jar");

    createJarFile("test-1.0.v2.jar");
    assertThat(new DeployedJar(jarFile).getDeployedFileName()).isEqualTo("test-1.0.jar");

    createJarFile("test-1.1.v3.jar");
    assertThat(new DeployedJar(jarFile).getDeployedFileName()).isEqualTo("test-1.1.jar");
  }