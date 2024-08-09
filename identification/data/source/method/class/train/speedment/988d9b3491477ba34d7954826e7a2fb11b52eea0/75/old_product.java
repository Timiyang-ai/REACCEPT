public static <T> ToLong<T> pow(ToByte<T> expression, int power) {
        switch (power) {
            case 0 : return object -> 1;
            case 1 : return expression.asLong();
            case 2 : return object -> {
                final byte v = expression.applyAsByte(object);
                return v * v;
            };
            case 3 : return object -> {
                final byte v = expression.applyAsByte(object);
                return v * v * v;
            };
        }

        return object -> (long) Math.pow(expression.applyAsByte(object), power);
    }