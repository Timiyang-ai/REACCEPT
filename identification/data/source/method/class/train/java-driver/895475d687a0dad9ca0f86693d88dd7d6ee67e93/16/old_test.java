  @Test(groups = "unit")
  @SuppressWarnings({"serial", "deprecation"})
  public void insertTest() throws Exception {

    String query;
    Statement insert;

    query =
        "INSERT INTO foo (a,b,\"C\",d) VALUES (123,'127.0.0.1','foo''bar',{'x':3,'y':2}) USING TIMESTAMP 42 AND TTL 24;";
    insert =
        insertInto("foo")
            .value("a", 123)
            .value("b", InetAddress.getByName("127.0.0.1"))
            .value(quote("C"), "foo'bar")
            .value(
                "d",
                new TreeMap<String, Integer>() {
                  {
                    put("x", 3);
                    put("y", 2);
                  }
                })
            .using(timestamp(42))
            .and(ttl(24));
    assertEquals(insert.toString(), query);

    query = "INSERT INTO foo (a,b) VALUES (2,null);";
    insert = insertInto("foo").value("a", 2).value("b", null);
    assertEquals(insert.toString(), query);

    query = "INSERT INTO foo (a,b) VALUES ({2,3,4},3.4) USING TTL 24 AND TIMESTAMP 42;";
    insert =
        insertInto("foo")
            .values(
                new String[] {"a", "b"},
                new Object[] {
                  new TreeSet<Integer>() {
                    {
                      add(2);
                      add(3);
                      add(4);
                    }
                  },
                  3.4
                })
            .using(ttl(24))
            .and(timestamp(42));
    assertEquals(insert.toString(), query);

    query = "INSERT INTO foo.bar (a,b) VALUES ({2,3,4},3.4) USING TTL ? AND TIMESTAMP ?;";
    insert =
        insertInto("foo", "bar")
            .values(
                new String[] {"a", "b"},
                new Object[] {
                  new TreeSet<Integer>() {
                    {
                      add(2);
                      add(3);
                      add(4);
                    }
                  },
                  3.4
                })
            .using(ttl(bindMarker()))
            .and(timestamp(bindMarker()));
    assertEquals(insert.toString(), query);

    // commutative result of TIMESTAMP
    query = "INSERT INTO foo.bar (a,b,c) VALUES ({2,3,4},3.4,123) USING TIMESTAMP 42;";
    insert =
        insertInto("foo", "bar")
            .using(timestamp(42))
            .values(
                new String[] {"a", "b"},
                new Object[] {
                  new TreeSet<Integer>() {
                    {
                      add(2);
                      add(3);
                      add(4);
                    }
                  },
                  3.4
                })
            .value("c", 123);
    assertEquals(insert.toString(), query);

    // commutative result of value() and values()
    query = "INSERT INTO foo (c,a,b) VALUES (123,{2,3,4},3.4) USING TIMESTAMP 42;";
    insert =
        insertInto("foo")
            .using(timestamp(42))
            .value("c", 123)
            .values(
                new String[] {"a", "b"},
                new Object[] {
                  new TreeSet<Integer>() {
                    {
                      add(2);
                      add(3);
                      add(4);
                    }
                  },
                  3.4
                });
    assertEquals(insert.toString(), query);

    try {
      insertInto("foo").values(new String[] {"a", "b"}, new Object[] {1, 2, 3});
      fail("Expected an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Got 2 names but 3 values");
    }

    // CAS test
    query = "INSERT INTO foo (k,x) VALUES (0,1) IF NOT EXISTS;";
    insert = insertInto("foo").value("k", 0).value("x", 1).ifNotExists();
    assertEquals(insert.toString(), query);

    // Tuples: see QueryBuilderTupleExecutionTest
    // UDT: see QueryBuilderExecutionTest
  }