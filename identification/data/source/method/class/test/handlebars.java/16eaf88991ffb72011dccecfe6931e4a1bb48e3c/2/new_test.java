  @Test
  public void i18nJs() throws Exception {
    PrecompilePlugin plugin = new PrecompilePlugin();
    plugin.setPrefix("src/test/resources/i18nJs");
    plugin.setSuffix(".html");
    plugin.setOutput("target/helpers-i18njs.js");
    plugin.setProject(newProject());
    plugin.setHandlebarsJsFile("/handlebars-v4.0.4.js");

    plugin.execute();

    assertEquals(FileUtils.fileRead("src/test/resources/helpers-i18njs.expected"),
        FileUtils.fileRead("target/helpers-i18njs.js"));
  }