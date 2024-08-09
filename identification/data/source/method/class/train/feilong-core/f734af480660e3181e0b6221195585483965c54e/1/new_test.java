@Test
    public void testToString22(){
        int[] int1 = { 2, 1 };
        assertEquals("2,1", ConvertUtil.toString(new ToStringConfig(","), int1));
        assertEquals("2", ConvertUtil.toString(new ToStringConfig(","), 2));
        assertEquals(",,,", ConvertUtil.toString(new ToStringConfig(",", true), ",", ","));
        assertEquals("2,", ConvertUtil.toString(new ToStringConfig(",", true), new Integer(2), null));
    }