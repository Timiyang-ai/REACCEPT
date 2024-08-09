@Test
    public void testGetSequence ()
    {
        System.out.println("\n+++ getSequence");

        int index = 1;
        RunTable instance = createHorizontalInstance();
        RunSequence expResult = new RunSequence(new short[]{1, 3, 2});
        System.out.println("expResult: " + expResult);

        RunSequence result = instance.getSequence(index);
        assertEquals(expResult, result);
    }