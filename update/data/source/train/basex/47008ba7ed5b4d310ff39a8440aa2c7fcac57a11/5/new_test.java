@Test
  public void installJar() {
    // ensure that all files are installed
    execute(new RepoInstall(REPO + "Hello.jar", null));
    final IOFile jar = new IOFile(REPO, "org/basex/modules/Hello.jar");
    final IOFile xqm = new IOFile(REPO, "org/basex/modules/Hello.xqm");
    assertTrue("File not found: " + jar, jar.exists());
    assertTrue("File not found: " + xqm, xqm.exists());

    // run query
    String query = "import module namespace h='http://basex.org/modules/Hello';h:hello('Universe')";
    assertEquals("Hello Universe", execute(new XQuery(query)));

    // run query, ensure that wrong types will be rejected
    query = "import module namespace h='http://basex.org/modules/Hello';h:hello(123)";
    try(QueryProcessor qp = new QueryProcessor(query, context)) {
      qp.value();
    } catch(final QueryException ex) {
      assertEquals(QueryError.INVPROMOTE_X, ex.error());
    }

    // ensure that all files were deleted
    execute(new RepoDelete("org.basex.modules.Hello", null));
    assertFalse("File was not deleted:" + jar, jar.exists());
    assertFalse("File was not deleted:" + xqm, xqm.exists());

    // ensure that package can only be deleted once
    try {
      new RepoDelete("org.basex.modules.Hello", null).execute(context);
    } catch(final BaseXException ex) {
      assertTrue(ex.toString().contains(REPO_NOTFOUND_X.code));
    }
  }