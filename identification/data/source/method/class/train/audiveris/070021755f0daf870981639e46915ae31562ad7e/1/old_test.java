@Test
    public void testToDouble ()
    {
        System.out.println("toDouble");

        Rational instance = new Rational(2, 4);
        double expResult = 0.5;
        double result = instance.toDouble();
        assertEquals(expResult, result, 0.0);
    }