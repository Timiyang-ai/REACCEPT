  @Test(groups = "unit")
  public void deleteTest() throws Exception {

    String query;
    Statement delete;

    query = "DELETE a,b,c FROM foo USING TIMESTAMP 0 WHERE k=1;";
    delete = delete("a", "b", "c").from("foo").using(timestamp(0)).where(eq("k", 1));
    assertEquals(delete.toString(), query);

    query = "DELETE a[3],b['foo'],c FROM foo WHERE k=1;";
    delete = delete().listElt("a", 3).mapElt("b", "foo").column("c").from("foo").where(eq("k", 1));
    assertEquals(delete.toString(), query);

    query = "DELETE a[?],b[?],c FROM foo WHERE k=1;";
    delete =
        delete()
            .listElt("a", bindMarker())
            .mapElt("b", bindMarker())
            .column("c")
            .from("foo")
            .where(eq("k", 1));
    assertEquals(delete.toString(), query);

    // Invalid CQL, testing edge case
    query = "DELETE a,b,c FROM foo;";
    delete = delete("a", "b", "c").from("foo");
    assertEquals(delete.toString(), query);

    query = "DELETE FROM foo USING TIMESTAMP 1240003134 WHERE k='value';";
    delete = delete().all().from("foo").using(timestamp(1240003134L)).where(eq("k", "value"));
    assertEquals(delete.toString(), query);
    delete = delete().from("foo").using(timestamp(1240003134L)).where(eq("k", "value"));
    assertEquals(delete.toString(), query);

    query = "DELETE a,b,c FROM foo.bar USING TIMESTAMP 1240003134 WHERE k=1;";
    delete =
        delete("a", "b", "c")
            .from("foo", "bar")
            .where()
            .and(eq("k", 1))
            .using(timestamp(1240003134L));
    assertEquals(delete.toString(), query);

    query = "DELETE FROM foo.bar WHERE k1='foo' AND k2=1;";
    delete = delete().from("foo", "bar").where(eq("k1", "foo")).and(eq("k2", 1));
    assertEquals(delete.toString(), query);

    try {
      delete().column("a").all().from("foo");
      fail("Expected an IllegalStateException");
    } catch (IllegalStateException e) {
      assertEquals(e.getMessage(), "Some columns ([a]) have already been selected.");
    }

    try {
      delete().from("foo").using(timestamp(-1240003134L));
      fail("Expected an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid timestamp, must be positive");
    }

    query = "DELETE FROM foo.bar WHERE k1='foo' IF EXISTS;";
    delete = delete().from("foo", "bar").where(eq("k1", "foo")).ifExists();
    assertEquals(delete.toString(), query);

    query = "DELETE FROM foo.bar WHERE k1='foo' IF a=1 AND b=2;";
    delete = delete().from("foo", "bar").where(eq("k1", "foo")).onlyIf(eq("a", 1)).and(eq("b", 2));
    assertEquals(delete.toString(), query);

    query = "DELETE FROM foo WHERE k=:key;";
    delete = delete().from("foo").where(eq("k", bindMarker("key")));
    assertEquals(delete.toString(), query);
  }