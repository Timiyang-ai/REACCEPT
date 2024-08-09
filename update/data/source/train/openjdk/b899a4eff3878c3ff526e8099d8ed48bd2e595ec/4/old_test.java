@Test(groups={"tck"})
    public void test_minusHours_one() {
        LocalDateTime t =TEST_2007_07_15_12_30_40_987654321.with(LocalTime.MIDNIGHT);
        LocalDate d = t.getDate();

        for (int i = 0; i < 50; i++) {
            t = t.minusHours(1);

            if (i % 24 == 0) {
                d = d.minusDays(1);
            }

            assertEquals(t.getDate(), d);
            assertEquals(t.getHour(), (((-i + 23) % 24) + 24) % 24);
        }
    }