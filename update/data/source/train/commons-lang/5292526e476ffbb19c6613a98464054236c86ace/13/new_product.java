public static int hashCode(final Annotation a) {
        int result = 0;
        final Class<? extends Annotation> type = a.annotationType();
        for (final Method m : type.getDeclaredMethods()) {
            try {
                final Object value = m.invoke(a);
                if (value == null) {
                    throw new IllegalStateException(
                            String.format("Annotation method %s returned null", m));
                }
                result += hashMember(m.getName(), value);
            } catch (final RuntimeException ex) {
                throw ex;
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }