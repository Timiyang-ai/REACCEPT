public static <T> ToDouble<T> shortPowInt(ToShort<T> expression, ToInt<T> power) {
        return new ToIntPower<T, ToShort<T>>(expression, power) {
            @Override
            public double applyAsDouble(T object) {
                final int p = power.applyAsInt(object);
                if (p == 0) return 1;
                final short value = inner.applyAsShort(object);
                switch (p) {
                    case 1: return value;
                    case 2: return value * value;
                    case 3: return value * value * value;
                    case 4: return value * value * value * value;
                    case 5: return value * value * value * value * value;
                    case -1: return 1 / value;
                    default: return Math.pow(value, p);
                }
            }
        };
    }