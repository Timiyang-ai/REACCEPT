@Test(groups={"tck"})
    public void test_minusSeconds_one() {
        LocalDateTime t = TEST_2007_07_15_12_30_40_987654321.with(LocalTime.MIDNIGHT);
        LocalDate d = t.getDate().minusDays(1);

        int hour = 0;
        int min = 0;
        int sec = 0;

        for (int i = 0; i < 3700; i++) {
            t = t.minusSeconds(1);
            sec--;
            if (sec == -1) {
                min--;
                sec = 59;

                if (min == -1) {
                    hour--;
                    min = 59;
                    
                    if (hour == -1) {
                        hour = 23;
                    }
                }
            }

            assertEquals(t.getDate(), d);
            assertEquals(t.getHour(), hour);
            assertEquals(t.getMinute(), min);
            assertEquals(t.getSecond(), sec);
        }
    }