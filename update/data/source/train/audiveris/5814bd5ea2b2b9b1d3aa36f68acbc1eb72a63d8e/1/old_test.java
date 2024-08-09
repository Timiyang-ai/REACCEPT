@Test
    public void testDump ()
    {
        System.out.println("dump");

        RunsTable instance = createHorizontalInstance();
        instance.dump(System.out);

        instance = createVerticalInstance();
        instance.dump(System.out);
    }