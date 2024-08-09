@Test
    public void testEquals() {
        VulnerableSoftware obj = new VulnerableSoftware();
        obj.setCpe("cpe:/a:mortbay:jetty:6.1.0");
        VulnerableSoftware instance = new VulnerableSoftware();
        instance.setCpe("cpe:/a:mortbay:jetty:6.1");
        assertFalse(instance.equals(obj));
    }