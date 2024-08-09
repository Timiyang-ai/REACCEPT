@Test
    public void testIsFlightFull() throws Exception {
        BitSet seats = new BitSet(SEATSConstants.NUM_SEATS_PER_FLIGHT);
        
        int seatnum = rand.nextInt(SEATSConstants.NUM_SEATS_PER_FLIGHT);
        assertFalse(seats.get(seatnum));
        seats.set(seatnum);
        assertTrue(seats.get(seatnum));
        assertFalse(SEATSClient.isFlightFull(seats));
    }