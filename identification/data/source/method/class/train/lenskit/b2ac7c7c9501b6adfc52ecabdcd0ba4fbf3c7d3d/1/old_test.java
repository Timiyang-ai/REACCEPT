@Test
    public void testSetDamping() {
        module.setDamping(500);
        assertEquals(500, module.getDamping(), EPSILON);
    }