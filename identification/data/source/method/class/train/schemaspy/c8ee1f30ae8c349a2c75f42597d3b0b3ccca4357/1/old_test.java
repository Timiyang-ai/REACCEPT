    @Test
    public void escapeHtml() {
        Database database = mock(Database.class);
        Table table = new LogicalTable(database, "catalog", "schema", "<table>", "comment");
        DotConfig dotConfig = new DotConfigUsingConfig(Config.getInstance(), false);
        DotNode dotNode = new DotNode(table,false, new DotNodeConfig(true, true), dotConfig);
        assertThat(dotNode.toString()).contains("tooltip=\"&lt;table&gt;");
    }