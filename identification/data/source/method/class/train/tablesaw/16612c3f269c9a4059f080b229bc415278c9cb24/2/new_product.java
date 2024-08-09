public static Column newColumn(@Nonnull String name,
                                   @Nonnull ColumnType type) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),
                "There must be a valid name for a new column");

        Preconditions.checkArgument(type != ColumnType.SKIP,
                "SKIP-ped columns should be handled outside of this method.");

        switch (type) {
            case LOCAL_DATE:
                return DateColumn.create(name);
            case LOCAL_TIME:
                return TimeColumn.create(name);
            case LOCAL_DATE_TIME:
                return DateTimeColumn.create(name);
            case NUMBER:
                return NumberColumn.create(name);
            case BOOLEAN:
                return BooleanColumn.create(name);
            case STRING:
                return StringColumn.create(name);
            default:
                throw new IllegalArgumentException("Unknown ColumnType: " + type);
        }
    }