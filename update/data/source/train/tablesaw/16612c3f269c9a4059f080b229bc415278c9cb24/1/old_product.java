public static Column newColumn(@Nonnull String name,
                                   @Nonnull ColumnType type) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),
                "There must be a valid name for a new column");

        Preconditions.checkArgument(type != ColumnType.SKIP,
                "SKIP-ped columns should be handled outside of this method.");

        switch (type) {
            case LOCAL_DATE:
                return new DateColumn(name);
            case LOCAL_TIME:
                return new TimeColumn(name);
            case LOCAL_DATE_TIME:
                return new DateTimeColumn(name);
            case INTEGER:
                return new IntColumn(name);
            case FLOAT:
                return new FloatColumn(name);
            case DOUBLE:
                return new DoubleColumn(name);
            case BOOLEAN:
                return new BooleanColumn(name);
            case CATEGORY:
                return new CategoryColumn(name);
            case SHORT_INT:
                return new ShortColumn(name);
            case LONG_INT:
                return new LongColumn(name);
            default:
                throw new IllegalArgumentException("Unknown ColumnType: " + type);
        }
    }