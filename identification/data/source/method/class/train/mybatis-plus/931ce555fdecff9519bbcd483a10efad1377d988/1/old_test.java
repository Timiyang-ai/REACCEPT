@Test
    public void testLimit() {
        ew.where("name={0}", "'123'").orderBy("id", false);
        ew.limit(0, 3);
        String sqlSegment = ew.toString();
        System.err.println("testLimit = " + sqlSegment);
        Assert.assertEquals("WHERE (name=?)\nORDER BY id DESC LIMIT 0, 3 ", sqlSegment);
    }