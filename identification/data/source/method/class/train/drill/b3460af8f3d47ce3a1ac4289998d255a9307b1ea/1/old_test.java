@Test
  public void testConnect() throws Exception {
    Class.forName("org.apache.drill.jdbc.Driver");
    final Connection connection = DriverManager.getConnection("jdbc:drillref:schema=DONUTS");
    connection.close();
  }