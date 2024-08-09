@Test
    public void testToString ()
    {
        System.out.println("\n+++ toString");

        RunTable instance = createHorizontalInstance();
        String expResult = "RunTable{hori HORIZONTAL 10x5}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }