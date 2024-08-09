public static int hashCode(final Annotation a) {
        int result = 0;
        Class<? extends Annotation> type = a.annotationType();
        for (Method m : type.getDeclaredMethods()) {
            try {
                Object value = m.invoke(a);
                if (value == null) {
                    throw new IllegalStateException(
                            String.format("Annotation method %s returned null", m));
                }
                result += hashMember(m.getName(), value);
            } catch (RuntimeException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return result;
    }