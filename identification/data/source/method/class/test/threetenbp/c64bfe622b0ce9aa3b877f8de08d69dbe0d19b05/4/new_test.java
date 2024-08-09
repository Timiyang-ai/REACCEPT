@Test(groups={"tck"})
    public void test_plusHours_one() {
        LocalDateTime t = TEST_2007_07_15_12_30_40_987654321.with(LocalTime.MIDNIGHT);
        LocalDate d = t.getDate();

        for (int i = 0; i < 50; i++) {
            t = t.plusHours(1);

            if ((i + 1) % 24 == 0) {
                d = d.plusDays(1);
            }

            assertEquals(t.getDate(), d);
            assertEquals(t.getHour(), (i + 1) % 24);
        }
    }