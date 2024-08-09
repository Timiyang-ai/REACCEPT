@Test
    public void testGetSequence ()
    {
        System.out.println("getSequence");

        int index = 1;
        RunTable instance = createHorizontalInstance();
        RunSequence expResult = new BasicRunSequence();
        expResult.add(new Run(0, 1));
        expResult.add(new Run(4, 2));
        System.out.println("expResult: " + expResult);

        RunSequence result = instance.getSequence(index);
        assertEquals(expResult, result);
    }