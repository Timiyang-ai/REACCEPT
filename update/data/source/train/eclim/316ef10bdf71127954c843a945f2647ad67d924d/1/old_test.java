@Test
  public void execute()
    throws Exception
  {
    String result = (String)Eclim.execute(new String[]{"settings"});

    Properties properties = new Properties();
    properties.load(new ByteArrayInputStream(result.getBytes()));

    assertTrue("Missing org.eclim.user.email",
        properties.containsKey("org.eclim.user.email"));
    assertTrue("Missing org.eclim.user.name",
        properties.containsKey("org.eclim.user.name"));

    assertTrue("Missing org.eclim.project.copyright",
        properties.containsKey("org.eclim.project.copyright"));
    assertTrue("Missing org.eclim.project.version",
        properties.containsKey("org.eclim.project.version"));

    assertTrue("Missing org.eclim.java.logging.impl",
        properties.containsKey("org.eclim.java.logging.impl"));
    assertTrue("Missing org.eclim.java.validation.ignore.warnings",
        properties.containsKey("org.eclim.java.validation.ignore.warnings"));
    assertTrue("Missing org.eclipse.jdt.core.compiler.source",
        properties.containsKey("org.eclipse.jdt.core.compiler.source"));

    assertTrue("Missing org.eclim.java.doc.version",
        properties.containsKey("org.eclim.java.doc.version"));
  }