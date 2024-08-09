@Test
    public void testGetCpu()
    {
        System.out.println("getCpu");
        double result = instance.getCpu();
        System.out.println(result);
        assertTrue(result >= 0);
    }