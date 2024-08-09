@Test
    public void testGetSequence ()
    {
        System.out.println("\n+++ getSequence");

        int index = 1;
        RunTable instance = createHorizontalInstance();
        short[] expResult = new short[]{1, 3, 2};
        System.out.println("expResult: " + Arrays.toString(expResult));

        short[] result = instance.getSequence(index);
        Assert.assertArrayEquals(expResult, result);
    }