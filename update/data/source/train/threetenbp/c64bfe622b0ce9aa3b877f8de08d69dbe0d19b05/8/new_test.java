@Test(groups={"tck"})
    public void test_minusNanos_halfABillion() {
        LocalDateTime t = TEST_2007_07_15_12_30_40_987654321.with(LocalTime.MIDNIGHT);
        LocalDate d = t.getDate().minusDays(1);

        int hour = 0;
        int min = 0;
        int sec = 0;
        int nanos = 0;

        for (long i = 0; i < 3700 * 1000000000L; i+= 500000000) {
            t = t.minusNanos(500000000);
            nanos -= 500000000;

            if (nanos < 0) {
                sec--;
                nanos += 1000000000;

                if (sec == -1) {
                    min--;
                    sec += 60;
                    
                    if (min == -1) {
                        hour--;
                        min += 60;

                        if (hour == -1) {
                            hour += 24;
                        }
                    }
                }
            }

            assertEquals(t.getDate(), d);
            assertEquals(t.getHour(), hour);
            assertEquals(t.getMinute(), min);
            assertEquals(t.getSecond(), sec);
            assertEquals(t.getNano(), nanos);
        }
    }