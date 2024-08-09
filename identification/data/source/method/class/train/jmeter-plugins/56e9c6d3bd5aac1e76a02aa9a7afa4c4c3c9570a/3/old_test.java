@Test
    public void testGetMem()
    {
        System.out.println("getMem");
        long result = instance.getMem();
        System.out.println(result);
        assertTrue(result >= 0);
    }