@Test
    public void testOpen() {

        try {
            instance.commit();
        } catch (DatabaseException | SQLException ex) {
            fail(ex.getMessage());
        }
    }