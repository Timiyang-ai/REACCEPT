public Object newInstance(final Class<?> c) throws InstantiationException {

        if (isSerializable(c)) {
            try {
                return readObject(getSerializedBytes(c));
                // ///CLOVER:OFF
            } catch (final IOException e) {
                throw new RuntimeException("Failed to instantiate " + c.getName() + "'s mock: ", e);
            } catch (final ClassNotFoundException e) {
                throw new RuntimeException("Failed to instantiate " + c.getName() + "'s mock: ", e);
            }
            // ///CLOVER:ON
        }

        final Constructor<?> constructor = getConstructorToUse(c);
        final Object[] params = getArgsForTypes(constructor.getParameterTypes());
        try {
            return constructor.newInstance(params);
            // ///CLOVER:OFF
        } catch (final IllegalArgumentException e) {
            throw new RuntimeException("Failed to instantiate " + c.getName() + "'s mock: ", e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate " + c.getName() + "'s mock: ", e);
            // ///CLOVER:ON
        } catch (final InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate " + c.getName() + "'s mock: ", e);
        }
    }