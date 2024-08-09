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
        vs.setCpe("cpe:/a:some:dep:9.2.0.0-20090116170000");
        instance = new VulnerableSoftware();
        instance.setCpe("cpe:/a:some:dep:9.2.0.0-20090116170001");
        expResult = 1;
        result = instance.compareTo(vs);
        assertEquals(expResult, result);
    }