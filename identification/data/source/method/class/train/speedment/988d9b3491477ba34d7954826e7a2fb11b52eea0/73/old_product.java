public static <T> ToLong<T> pow(ToByte<T> expression, ToInt<T> power) {
        return object -> {
            final byte v = expression.applyAsByte(object);
            final int p = power.applyAsInt(object);
            switch (p) {
                case 0 : return 1;
                case 1 : return v;
                case 2 : return v * v;
                case 3 : return v * v * v;
            }
            return (long) Math.pow(v, p);
        };
    }