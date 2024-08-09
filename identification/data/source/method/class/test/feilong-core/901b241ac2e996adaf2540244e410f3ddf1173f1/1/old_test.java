@Test
    public void testToString22(){
        int[] int1 = { 2, 1 };
        assertEquals("2,1", ConvertUtil.toString(new ToStringConfig(","), int1));
    }