@Test
    public void testOpen() {
        CveDB instance = null;
        try {
            instance = CveDB.getInstance();
            instance.commit();
        } catch (DatabaseException | SQLException ex) {
            fail(ex.getMessage());
        } finally {
            int start = instance.getUsageCount();
            instance.close();
            int end = instance.getUsageCount();
            assertTrue( end < start);
        }
    }