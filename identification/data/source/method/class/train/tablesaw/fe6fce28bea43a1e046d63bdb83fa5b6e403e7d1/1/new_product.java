default FloatColumn difference(DateColumn column1, DateColumn column2, ChronoUnit unit) {

        FloatColumn newColumn = FloatColumn.create(column1.name() + " - " + column2.name());
        for (int r = 0; r < column1.size(); r++) {
            int c1 = column1.getInt(r);
            int c2 = column2.getInt(r);
            if (c1 == FloatColumn.MISSING_VALUE || c2 == FloatColumn.MISSING_VALUE) {
                newColumn.append(FloatColumn.MISSING_VALUE);
            } else {
                LocalDate value1 = PackedLocalDate.asLocalDate(c1);
                LocalDate value2 = PackedLocalDate.asLocalDate(c2);
                newColumn.append(unit.between(value1, value2));
            }
        }
        return newColumn;
    }