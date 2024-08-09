  @Test(groups = "unit")
  @SuppressWarnings("serial")
  public void updateTest() throws Exception {

    String query;
    Statement update;

    query = "UPDATE foo.bar USING TIMESTAMP 42 SET a=12,b=[3,2,1],c=c+3 WHERE k=2;";
    update =
        update("foo", "bar")
            .using(timestamp(42))
            .with(set("a", 12))
            .and(set("b", Arrays.asList(3, 2, 1)))
            .and(incr("c", 3))
            .where(eq("k", 2));
    assertEquals(update.toString(), query);

    query = "UPDATE foo SET b=null WHERE k=2;";
    update = update("foo").where().and(eq("k", 2)).with(set("b", null));
    assertEquals(update.toString(), query);

    query =
        "UPDATE foo SET a[2]='foo',b=[3,2,1]+b,c=c-{'a'} WHERE k=2 AND l='foo' AND m<4 AND n>=1;";
    update =
        update("foo")
            .with(setIdx("a", 2, "foo"))
            .and(prependAll("b", Arrays.asList(3, 2, 1)))
            .and(remove("c", "a"))
            .where(eq("k", 2))
            .and(eq("l", "foo"))
            .and(lt("m", 4))
            .and(gte("n", 1));
    assertEquals(update.toString(), query);

    query = "UPDATE foo SET b=[3]+b,c=c+['a'],d=d+[1,2,3],e=e-[1];";
    update =
        update("foo")
            .with()
            .and(prepend("b", 3))
            .and(append("c", "a"))
            .and(appendAll("d", Arrays.asList(1, 2, 3)))
            .and(discard("e", 1));
    assertEquals(update.toString(), query);

    query = "UPDATE foo SET b=b-[1,2,3],c=c+{1},d=d+{2,3,4};";
    update =
        update("foo")
            .with(discardAll("b", Arrays.asList(1, 2, 3)))
            .and(add("c", 1))
            .and(
                addAll(
                    "d",
                    new TreeSet<Integer>() {
                      {
                        add(2);
                        add(3);
                        add(4);
                      }
                    }));
    assertEquals(update.toString(), query);

    query = "UPDATE foo SET b=b-{2,3,4},c['k']='v',d=d+{'x':3,'y':2};";
    update =
        update("foo")
            .with(
                removeAll(
                    "b",
                    new TreeSet<Integer>() {
                      {
                        add(2);
                        add(3);
                        add(4);
                      }
                    }))
            .and(put("c", "k", "v"))
            .and(
                putAll(
                    "d",
                    new TreeMap<String, Integer>() {
                      {
                        put("x", 3);
                        put("y", 2);
                      }
                    }));
    assertEquals(update.toString(), query);

    query = "UPDATE foo USING TTL 400;";
    update = update("foo").using(ttl(400));
    assertEquals(update.toString(), query);

    query = "UPDATE foo SET a=" + new BigDecimal(3.2) + ",b=42 WHERE k=2;";
    update =
        update("foo")
            .with(set("a", new BigDecimal(3.2)))
            .and(set("b", new BigInteger("42")))
            .where(eq("k", 2));
    assertEquals(update.toString(), query);

    query = "UPDATE foo USING TIMESTAMP 42 SET b=[3,2,1]+b WHERE k=2 AND l='foo';";
    update =
        update("foo")
            .where()
            .and(eq("k", 2))
            .and(eq("l", "foo"))
            .with(prependAll("b", Arrays.asList(3, 2, 1)))
            .using(timestamp(42));
    assertEquals(update.toString(), query);

    // Test commutative USING
    update =
        update("foo")
            .where()
            .and(eq("k", 2))
            .and(eq("l", "foo"))
            .using(timestamp(42))
            .with(prependAll("b", Arrays.asList(3, 2, 1)));
    assertEquals(update.toString(), query);

    // Test commutative USING
    update =
        update("foo")
            .using(timestamp(42))
            .where(eq("k", 2))
            .and(eq("l", "foo"))
            .with(prependAll("b", Arrays.asList(3, 2, 1)));
    assertEquals(update.toString(), query);

    try {
      update("foo").using(ttl(-400));
      fail("Expected an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid ttl, must be positive");
    }

    // CAS test
    query = "UPDATE foo SET x=4 WHERE k=0 IF x=1;";
    update = update("foo").with(set("x", 4)).where(eq("k", 0)).onlyIf(eq("x", 1));
    assertEquals(update.toString(), query);

    // IF EXISTS CAS test
    update = update("foo").with(set("x", 3)).where(eq("k", 2)).ifExists();
    assertThat(update.toString()).isEqualTo("UPDATE foo SET x=3 WHERE k=2 IF EXISTS;");
  }