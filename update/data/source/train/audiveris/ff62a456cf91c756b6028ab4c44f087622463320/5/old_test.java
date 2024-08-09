@Test
    public void testSetSequence ()
    {
        System.out.println("setSequence");

        int index = 1;
        RunSequence seq = new BasicRunSequence(new short[]{0, 3, 7});
        RunTable instance = createHorizontalInstance();
        System.out.println("table before:" + instance.dumpOf());
        instance.setSequence(index, seq);
        System.out.println("table after:" + instance.dumpOf());
    }