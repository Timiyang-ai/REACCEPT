@Test
  @SuppressWarnings("unchecked")
  public void execute()
    throws Exception
  {
    String result = (String)Eclim.execute(new String[]{
      "history_clear", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
    });
    assertEquals(result, "History Cleared.");

    List<Map<String,Object>> results = (List<Map<String,Object>>)
      Eclim.execute(new String[]{
        "history_list", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
      });
    assertEquals(0, results.size());

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

    results = (List<Map<String,Object>>)
      Eclim.execute(new String[]{
        "history_list", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE
      });

    assertEquals(2, results.size());

    for (Map<String,Object> entry : results){
      assertTrue(ENTRY_TIMESTAMP.matcher(entry.get("timestamp").toString()).matches());
      assertTrue(ENTRY_DATETIME.matcher(entry.get("datetime").toString()).matches());
      assertTrue(ENTRY_DELTA.matcher(entry.get("delta").toString()).matches());
    }

    result = (String)Eclim.execute(new String[]{
      "history_revision", "-p", Eclim.TEST_PROJECT, "-f", TEST_FILE,
      "-r", results.get(1).get("timestamp").toString()
    });
    assertEquals("Wrong result.", result, "line 1\n");
  }