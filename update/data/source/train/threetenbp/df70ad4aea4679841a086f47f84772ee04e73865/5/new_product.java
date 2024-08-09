@DataProvider(name="matchesCalendrical")
    Iterator<Object[]> matchesCalendrical() {
        return new Iterator<Object[]>() {
            private LocalDate date = LocalDate.of(2008, 1, 1);

            public boolean hasNext() {
                return date.getYear() != 2009;
            }

            public Object[] next() {
                LocalDate ret = date;
                date = date.plusDays(1);
                return new Object[] {ret};
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }