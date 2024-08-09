@Test
    public void testIsSame_Column_Column() {
        final Column[] columns = new Column[] {
            columnA1, columnA2, columnB1, columnB2,
            columnC1, columnC2, columnD1, columnD2
        };
        
        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                assertEquals(
                    "  Is " + columns[i].getId() + 
                    " same as " + columns[j].getId() + 
                    ": ", i == j, 
                    DocumentDbUtil.isSame(columns[i], columns[j])
                );
            }
        }
    }