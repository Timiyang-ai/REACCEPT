  @Test(groups = "unit")
  public void selectTest() throws Exception {

    String query;
    Statement select;

    query = "SELECT * FROM foo WHERE k=4 AND c>'a' AND c<='z';";
    select = select().all().from("foo").where(eq("k", 4)).and(gt("c", "a")).and(lte("c", "z"));
    assertEquals(select.toString(), query);

    // Ensure where() and where(...) are equal
    select =
        select().all().from("foo").where().and(eq("k", 4)).and(gt("c", "a")).and(lte("c", "z"));
    assertEquals(select.toString(), query);

    query =
        "SELECT a,b,\"C\" FROM foo WHERE a IN ('127.0.0.1','127.0.0.3') AND \"C\"='foo' ORDER BY a ASC,b DESC LIMIT 42;";
    select =
        select("a", "b", quote("C"))
            .from("foo")
            .where(in("a", InetAddress.getByName("127.0.0.1"), InetAddress.getByName("127.0.0.3")))
            .and(eq(quote("C"), "foo"))
            .orderBy(asc("a"), desc("b"))
            .limit(42);
    assertEquals(select.toString(), query);

    query = "SELECT writetime(a),ttl(a) FROM foo ALLOW FILTERING;";
    select = select().writeTime("a").ttl("a").from("foo").allowFiltering();
    assertEquals(select.toString(), query);

    query = "SELECT DISTINCT longName AS a,ttl(longName) AS ttla FROM foo LIMIT :limit;";
    select =
        select()
            .distinct()
            .column("longName")
            .as("a")
            .ttl("longName")
            .as("ttla")
            .from("foo")
            .limit(bindMarker("limit"));
    assertEquals(select.toString(), query);

    query =
        "SELECT DISTINCT longName AS a,ttl(longName) AS ttla FROM foo WHERE k IN () LIMIT :limit;";
    select =
        select()
            .distinct()
            .column("longName")
            .as("a")
            .ttl("longName")
            .as("ttla")
            .from("foo")
            .where(in("k"))
            .limit(bindMarker("limit"));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE bar=:barmark AND baz=:bazmark LIMIT :limit;";
    select =
        select()
            .all()
            .from("foo")
            .where()
            .and(eq("bar", bindMarker("barmark")))
            .and(eq("baz", bindMarker("bazmark")))
            .limit(bindMarker("limit"));
    assertEquals(select.toString(), query);

    query = "SELECT a FROM foo WHERE k IN ();";
    select = select("a").from("foo").where(in("k"));
    assertEquals(select.toString(), query);

    query = "SELECT a FROM foo WHERE k IN ?;";
    select = select("a").from("foo").where(in("k", bindMarker()));
    assertEquals(select.toString(), query);

    query = "SELECT DISTINCT a FROM foo WHERE k=1;";
    select = select("a").distinct().from("foo").where(eq("k", 1));
    assertEquals(select.toString(), query);

    query = "SELECT DISTINCT a,b FROM foo WHERE k=1;";
    select = select("a", "b").distinct().from("foo").where(eq("k", 1));
    assertEquals(select.toString(), query);

    query = "SELECT count(*) FROM foo;";
    select = select().countAll().from("foo");
    assertEquals(select.toString(), query);

    query = "SELECT intToBlob(b) FROM foo;";
    select = select().fcall("intToBlob", column("b")).from("foo");
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE k>42 LIMIT 42;";
    select = select().all().from("foo").where(gt("k", 42)).limit(42);
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE token(k)>token(42);";
    select = select().all().from("foo").where(gt(token("k"), fcall("token", 42)));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo2 WHERE token(a,b)>token(42,101);";
    select = select().all().from("foo2").where(gt(token("a", "b"), fcall("token", 42, 101)));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM words WHERE w='):,ydL ;O,D';";
    select = select().all().from("words").where(eq("w", "):,ydL ;O,D"));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM words WHERE w='WA(!:gS)r(UfW';";
    select = select().all().from("words").where(eq("w", "WA(!:gS)r(UfW"));
    assertEquals(select.toString(), query);

    Date date = new Date();
    date.setTime(1234325);
    query = "SELECT * FROM foo WHERE d=1234325;";
    select = select().all().from("foo").where(eq("d", date));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE b=0xcafebabe;";
    select = select().all().from("foo").where(eq("b", Bytes.fromHexString("0xCAFEBABE")));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE e CONTAINS 'text';";
    select = select().from("foo").where(contains("e", "text"));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE e CONTAINS KEY 'key1';";
    select = select().from("foo").where(containsKey("e", "key1"));
    assertEquals(select.toString(), query);

    query = "SELECT CAST(writetime(country) AS text) FROM artists LIMIT 2;";
    select =
        select()
            .cast(fcall("writetime", column("country")), DataType.text())
            .from("artists")
            .limit(2);
    assertEquals(select.toString(), query);

    query = "SELECT avg(CAST(v AS float)) FROM e;";
    select = select().fcall("avg", cast(column("v"), DataType.cfloat())).from("e");
    assertEquals(select.toString(), query);

    query = "SELECT CAST(writetime(country) AS text) FROM artists LIMIT 2;";
    select = select().raw("CAST(writetime(country) AS text)").from("artists").limit(2);
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE e LIKE 'a%';";
    select = select().from("foo").where(like("e", "a%"));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE k!=1;";
    select = select().from("foo").where(ne("k", 1));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE (k1,k2)!=(1,2);";
    select = select().from("foo").where(ne(ImmutableList.of("k1", "k2"), ImmutableList.of(1, 2)));
    assertEquals(select.toString(), query);

    query = "SELECT * FROM foo WHERE k IS NOT NULL;";
    select = select().from("foo").where(notNull("k"));
    assertEquals(select.toString(), query);

    try {
      select().countAll().from("foo").orderBy(asc("a"), desc("b")).orderBy(asc("a"), desc("b"));
      fail("Expected an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "An ORDER BY clause has already been provided");
    }

    try {
      select().from("foo").orderBy();
      fail("Expected an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid ORDER BY argument, the orderings must not be empty.");
    }

    try {
      select().column("a").all().from("foo");
      fail("Expected an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Some columns ([a]) have already been selected.");
    }

    try {
      select().column("a").countAll().from("foo");
      fail("Expected an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Some columns ([a]) have already been selected.");
    }

    try {
      select().all().from("foo").limit(-42);
      fail("Expected an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid LIMIT value, must be strictly positive");
    }

    try {
      select().all().from("foo").limit(42).limit(42);
      fail("Expected an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "A LIMIT value has already been provided");
    }
  }