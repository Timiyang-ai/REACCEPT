@Test
    public void testIsFlightFull() throws Exception {
        BitSet seats = new BitSet(SEATSConstants.FLIGHTS_NUM_SEATS);
        
        int seatnum = rand.nextInt(SEATSConstants.FLIGHTS_NUM_SEATS);
        assertFalse(seats.get(seatnum));
        seats.set(seatnum);
        assertTrue(seats.get(seatnum));
        assertFalse(client.isFlightFull(seats));
    }