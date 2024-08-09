    private static ResourceLimits.Builder cpuTimeLimit(ResourceLimits.Builder builder, Duration timeLimit, Duration accuracy) {
        try {
            Method m = builder.getClass().getDeclaredMethod("cpuTimeLimit", Duration.class, Duration.class);
            ReflectionUtils.setAccessible(m, true);
            m.invoke(builder, timeLimit, accuracy);
        } catch (InvocationTargetException e) {
            throw (RuntimeException) e.getCause();
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
            throw new AssertionError(e);
        }
        return builder;
    }