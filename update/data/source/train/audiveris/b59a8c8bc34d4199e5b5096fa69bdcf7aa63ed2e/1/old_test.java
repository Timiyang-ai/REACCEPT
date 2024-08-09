@Test
    public void testPrint ()
    {
        System.out.println("print");

        PrintStream        stream = System.out;
        Histogram<Integer> instance = createHistogram();
        instance.print(stream);
    }