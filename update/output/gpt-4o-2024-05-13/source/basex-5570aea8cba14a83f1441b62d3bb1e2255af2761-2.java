@Test
public void serialize() {
    // Test case with invalid option
    final String query = "csv:serialize(<csv/>, {'x':'y'})";
    error(query, Err.INVALIDOPT);
}