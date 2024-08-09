@Test
  @SuppressWarnings("unchecked")
  public void execute()
    throws Exception
  {
    List<Map<String,String>> results = (List<Map<String,String>>)
      Eclim.execute(new String[]{"settings"});

    HashMap<String,String> properties = new HashMap<String,String>();
    for (Map<String,String> result : results){
      properties.put(result.get("name"), result.get("value"));
    }

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
    assertTrue("Missing org.eclipse.jdt.core.compiler.source",
        properties.containsKey("org.eclipse.jdt.core.compiler.source"));

    assertTrue("Missing org.eclim.java.doc.version",
        properties.containsKey("org.eclim.java.doc.version"));
  }