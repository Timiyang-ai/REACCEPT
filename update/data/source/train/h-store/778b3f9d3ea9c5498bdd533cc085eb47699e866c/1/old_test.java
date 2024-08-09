@Test
    public void testAddToTableSize() {
        for (String table_name : AuctionMarkConstants.TABLENAMES) {
            long count = this.profile.getTableSize(table_name);
            assert(count > 0) : "Unexpected count for " + table_name;
            long delta = this.rand.nextInt();
            this.profile.addToTableSize(table_name, delta);
            assertEquals(count + delta, this.profile.getTableSize(table_name));
        } // FOR
    }