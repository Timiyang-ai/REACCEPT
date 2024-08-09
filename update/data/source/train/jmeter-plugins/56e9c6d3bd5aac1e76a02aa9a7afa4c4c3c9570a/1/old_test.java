@Test
    public void testConnect() throws Exception
    {
        System.out.println("connect");
        instance = new AgentConnector("localhost", testPort);
        instance.connect();
    }