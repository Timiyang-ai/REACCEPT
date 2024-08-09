@Test
    public void test_lengthOfMonth_notLeapYear() {
        assertEquals(LocalDate.of(2007, 1, 1).lengthOfMonth(), 31);
        assertEquals(LocalDate.of(2007, 2, 1).lengthOfMonth(), 28);
        assertEquals(LocalDate.of(2007, 3, 1).lengthOfMonth(), 31);
        assertEquals(LocalDate.of(2007, 4, 1).lengthOfMonth(), 30);
        assertEquals(LocalDate.of(2007, 5, 1).lengthOfMonth(), 31);
        assertEquals(LocalDate.of(2007, 6, 1).lengthOfMonth(), 30);
        assertEquals(LocalDate.of(2007, 7, 1).lengthOfMonth(), 31);
        assertEquals(LocalDate.of(2007, 8, 1).lengthOfMonth(), 31);
        assertEquals(LocalDate.of(2007, 9, 1).lengthOfMonth(), 30);
        assertEquals(LocalDate.of(2007, 10, 1).lengthOfMonth(), 31);
        assertEquals(LocalDate.of(2007, 11, 1).lengthOfMonth(), 30);
        assertEquals(LocalDate.of(2007, 12, 1).lengthOfMonth(), 31);
    }