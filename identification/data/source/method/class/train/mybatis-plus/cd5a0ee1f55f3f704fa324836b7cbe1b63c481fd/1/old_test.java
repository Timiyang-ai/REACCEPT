@Test
    public void testInstance() {
        String val1 = "'''";
        String val2 = "\\";
        String sqlPart = Condition.instance().between("test_type", val1, val2).toString();
        System.out.println("sql ==> " + sqlPart);
        Assert.assertEquals("WHERE (test_type BETWEEN ? AND ?)", sqlPart);
    }