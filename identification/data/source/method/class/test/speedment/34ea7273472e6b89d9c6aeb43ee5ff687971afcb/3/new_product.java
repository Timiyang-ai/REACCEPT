public static <T> ToDouble<T> longPowInt(ToLong<T> expression, ToInt<T> power) {
        return new ToIntPower<T, ToLong<T>>(expression, power) {
            @Override
            public double applyAsDouble(T object) {
                final int p = power.applyAsInt(object);
                if (p == 0) return 1;
                final double value = inner.applyAsLong(object);
                switch (p) {
                    case 1: return value;
                    case 2: return value * value;
                    case 3: return value * value * value;
                    case 4: return value * value * value * value;
                    case 5: return value * value * value * value * value;
                    case -1: return 1d / value;
                    default: return Math.pow(value, p);
                }
            }
        };
    }