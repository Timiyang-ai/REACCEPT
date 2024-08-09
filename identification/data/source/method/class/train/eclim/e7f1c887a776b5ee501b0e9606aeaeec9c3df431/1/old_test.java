@Test
  public void execute()
    throws Exception
  {
    String result = (String)Eclim.execute(new String[]{
      "history_clear", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });
    assertEquals("Wrong result.", result, "History Cleared.");

    result = (String)Eclim.execute(new String[]{
      "history_list", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });
    assertEquals("Wrong result.", result, "[]");

    assertEquals("Wrong file contents.",
        Eclim.fileToString(Eclim.TEST_PROJECT, TEST_FILE), "line 1\n");

    Eclim.execute(new String[]{
      "history_add", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });
    Eclim.execute(new String[]{
      "project_refresh_file", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });

    BufferedWriter out = null;
    try{
      out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(Eclim.resolveFile(TEST_FILE), true)));
      out.write("line 2");
    }finally{
      try{
        out.close();
      }catch(Exception ignore){
      }
    }

    Eclim.execute(new String[]{
      "history_add", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });
    Eclim.execute(new String[]{
      "project_refresh_file", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });

    result = (String)Eclim.execute(new String[]{
      "history_list", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });

    Pattern pattern = Pattern.compile("^\\[" + ENTRY + "," + ENTRY + "\\]$");
    Matcher matcher = pattern.matcher(result);
    assertTrue("Wrong result.", matcher.matches());

    pattern = Pattern.compile(ENTRY);
    matcher = pattern.matcher(result);
    matcher.find();
    matcher.find();
    String ts = matcher.group(1);
    System.out.println(ts);

    result = (String)Eclim.execute(new String[]{
      "history_revision", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE, "-r", ts
    });
    assertEquals("Wrong result.", result, "line 1\n");
  }