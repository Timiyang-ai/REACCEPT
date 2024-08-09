protected static boolean isFlightFull(BitSet seats) {
        assert(FULL_FLIGHT_BITSET.size() == seats.size());
        return FULL_FLIGHT_BITSET.equals(seats);
    }