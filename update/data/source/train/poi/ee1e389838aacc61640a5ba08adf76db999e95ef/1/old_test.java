@Override
    @Test
    public void cloneSheet() throws IOException {
        try {
            super.cloneSheet();
            fail("expected exception");
        } catch (RuntimeException e){
            assertEquals("NotImplemented", e.getMessage());
        }
    }