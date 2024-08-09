  @Test
  public void downloadPackage() throws TaskRunnerException {
    HeliumPackage pkg =
        new HeliumPackage(
            HeliumType.VISUALIZATION,
            "lodash",
            "lodash",
            "lodash@3.9.3",
            "",
            null,
            "license",
            "icon");
    hbf.install(pkg);
    System.out.println(new File(nodeInstallationDir, "/node_modules/lodash"));
    assertTrue(new File(nodeInstallationDir, "/node_modules/lodash").isDirectory());
  }