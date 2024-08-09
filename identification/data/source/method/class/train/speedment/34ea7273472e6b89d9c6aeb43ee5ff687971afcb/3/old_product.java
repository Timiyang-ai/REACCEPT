public static <T> ToDouble<T> bytePowInt(ToByte<T> expression, int power) {
        switch (power) {
            case 0: return ToDouble.constant(1);
            case 1: return expression.asDouble();
            case 2: return new IntPower<T, ToByte<T>>(expression, 2) {
                @Override
                public double applyAsDouble(T object) {
                    final byte value = inner.applyAsByte(object);
                    return value * value;
                }
            };
            case 3: return new IntPower<T, ToByte<T>>(expression, 3) {
                @Override
                public double applyAsDouble(T object) {
                    final byte value = inner.applyAsByte(object);
                    return value * value * value;
                }
            };
            case -1: return new IntPower<T, ToByte<T>>(expression, -1) {
                @Override
                public double applyAsDouble(T object) {
                    return 1 / inner.applyAsByte(object);
                }
            };
            default: return new IntPower<T, ToByte<T>>(expression, power) {
                @Override
                public double applyAsDouble(T object) {
                    return Math.pow(inner.applyAsByte(object), this.power);
                }
            };
        }
    }