@Test
    public void testEquals() {
        DependencyVersion obj = new DependencyVersion("1.2.3.r1");
        DependencyVersion instance = new DependencyVersion("1.2.3");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = new DependencyVersion("1.2.3");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        instance = new DependencyVersion("2.0.0");
        obj = new DependencyVersion("2");
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new DependencyVersion("2.0");
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        
    }