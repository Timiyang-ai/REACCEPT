@Test
    public void testCompareTo() {
        VulnerableSoftware vs = new VulnerableSoftware();
        vs.setCpe("cpe:/a:mortbay:jetty:6.1.0");
        VulnerableSoftware instance = new VulnerableSoftware();
        instance.setCpe("cpe:/a:mortbay:jetty:6.1");
        int expResult = -2;
        int result = instance.compareTo(vs);
        assertEquals(expResult, result);

        vs = new VulnerableSoftware();
        vs.setCpe("cpe:/a:yahoo:toolbar:3.1.0.20130813024103");
        instance = new VulnerableSoftware();
        instance.setCpe("cpe:/a:yahoo:toolbar:3.1.0.20130813024104");
        expResult = 1;
        result = instance.compareTo(vs);
        assertEquals(expResult, result);
    }