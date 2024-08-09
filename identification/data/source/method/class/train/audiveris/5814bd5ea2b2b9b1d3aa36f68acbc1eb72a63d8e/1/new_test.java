@Test
    public void testDump ()
    {
        System.out.println("dump");

        RunsTable instance = createHorizontalInstance();
        System.out.println(instance.dumpOf());

        instance = createVerticalInstance();
        System.out.println(instance.dumpOf());
    }