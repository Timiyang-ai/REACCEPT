public static <T> ToLong<T> pow(ToShort<T> expression, int power) {
        switch (power) {
            case 0 : return object -> 1;
            case 1 : return expression.asLong();
            case 2 : return object -> {
                final short v = expression.applyAsShort(object);
                return v * v;
            };
            case 3 : return object -> {
                final short v = expression.applyAsShort(object);
                return v * v * v;
            };
        }

        return object -> (long) Math.pow(
            expression.applyAsShort(object),
            power
        );
    }