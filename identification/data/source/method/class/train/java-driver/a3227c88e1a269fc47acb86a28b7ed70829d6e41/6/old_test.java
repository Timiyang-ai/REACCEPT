  @Test(groups = "unit")
  @SuppressWarnings("serial")
  public void batchTest() throws Exception {
    String query;
    Statement batch;

    query = "BEGIN BATCH USING TIMESTAMP 42 ";
    query += "INSERT INTO foo (a,b) VALUES ({2,3,4},3.4);";
    query += "UPDATE foo SET a[2]='foo',b=[3,2,1]+b,c=c-{'a'} WHERE k=2;";
    query += "DELETE a[3],b['foo'],c FROM foo WHERE k=1;";
    query += "APPLY BATCH;";
    batch =
        batch()
            .add(
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
                        }))
            .add(
                update("foo")
                    .with(setIdx("a", 2, "foo"))
                    .and(prependAll("b", Arrays.asList(3, 2, 1)))
                    .and(remove("c", "a"))
                    .where(eq("k", 2)))
            .add(
                delete()
                    .listElt("a", 3)
                    .mapElt("b", "foo")
                    .column("c")
                    .from("foo")
                    .where(eq("k", 1)))
            .using(timestamp(42));
    assertEquals(batch.toString(), query);

    // Test passing batch(statement)
    query = "BEGIN BATCH ";
    query += "DELETE a[3] FROM foo WHERE k=1;";
    query += "APPLY BATCH;";
    batch = batch(delete().listElt("a", 3).from("foo").where(eq("k", 1)));
    assertEquals(batch.toString(), query);

    assertEquals(batch().toString(), "BEGIN BATCH APPLY BATCH;");
  }