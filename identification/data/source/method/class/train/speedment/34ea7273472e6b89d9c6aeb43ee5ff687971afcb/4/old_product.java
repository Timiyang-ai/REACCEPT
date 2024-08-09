public static <T> ToDouble<T> shortPowInt(ToShort<T> expression, int power) {
        switch (power) {
            case 0: return ToDouble.constant(1);
            case 1: return expression.asDouble();
            case 2: return new IntPower<T, ToShort<T>>(expression, 2) {
                @Override
                public double applyAsDouble(T object) {
                    final short value = inner.applyAsShort(object);
                    return value * value;
                }
            };
            case 3: return new IntPower<T, ToShort<T>>(expression, 3) {
                @Override
                public double applyAsDouble(T object) {
                    final short value = inner.applyAsShort(object);
                    return value * value * value;
                }
            };
            case -1: return new IntPower<T, ToShort<T>>(expression, -1) {
                @Override
                public double applyAsDouble(T object) {
                    return 1 / inner.applyAsShort(object);
                }
            };
            default: return new IntPower<T, ToShort<T>>(expression, power) {
                @Override
                public double applyAsDouble(T object) {
                    return Math.pow(inner.applyAsShort(object), this.power);
                }
            };
        }
    }