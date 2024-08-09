@Test
    public void testSetDamping() {
        module.setMeanDamping(500);
        assertEquals(500, module.getMeanDamping(), EPSILON);
    }