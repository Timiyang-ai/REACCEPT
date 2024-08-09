@Test
  public void testConnect() throws Exception {
    Class.forName("org.apache.drill.jdbc.Driver");
    final Connection connection = DriverManager.getConnection("jdbc:drill:zk=local");
    connection.close();
  }