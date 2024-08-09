public static <T> ToDouble<T> intPowInt(ToInt<T> expression, int power) {
        switch (power) {
            case 0: return ToDouble.constant(1);
            case 1: return expression.asDouble();
            case 2: return new IntPower<T, ToInt<T>>(expression, 2) {
                @Override
                public double applyAsDouble(T object) {
                    final double value = inner.applyAsInt(object);
                    return value * value;
                }
            };
            case 3: return new IntPower<T, ToInt<T>>(expression, 3) {
                @Override
                public double applyAsDouble(T object) {
                    final double value = inner.applyAsInt(object);
                    return value * value * value;
                }
            };
            case -1: return new IntPower<T, ToInt<T>>(expression, -1) {
                @Override
                public double applyAsDouble(T object) {
                    return 1d / inner.applyAsInt(object);
                }
            };
            default: return new IntPower<T, ToInt<T>>(expression, power) {
                @Override
                public double applyAsDouble(T object) {
                    return Math.pow(inner.applyAsInt(object), this.power);
                }
            };
        }
    }