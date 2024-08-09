@Test
    public void testLimit() {
        ew.where("name={0}", "'123'").orderBy("id", false);
        ew.last("limit 1,2");
        String sqlSegment = ew.toString();
        System.err.println("testLimit = " + sqlSegment);
        Assert.assertEquals("WHERE (name=?)\nORDER BY id DESC limit 1,2", sqlSegment);
    }