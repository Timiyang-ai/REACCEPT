private static <T> String toStringImpl0(
        Class<T> cls,
        SBLimitedLength buf,
        T obj,
        Object[] addNames,
        Object[] addVals,
        @Nullable boolean[] addSens,
        int addLen
    ) {
        try {
            GridToStringClassDescriptor cd = getClassDescriptor(cls);

            assert cd != null;

            buf.a(cd.getSimpleClassName());

            EntryReference ref = savedObjects.get().get(obj);

            if (ref != null && ref.hashNeeded) {
                buf.a(identity(obj));

                ref.hashNeeded = false;
            }

            buf.a(" [");

            boolean first = true;

            for (GridToStringFieldDescriptor fd : cd.getFields()) {
                if (!first)
                    buf.a(", ");
                else
                    first = false;

                String name = fd.getName();

                Field field = cls.getDeclaredField(name);

                field.setAccessible(true);

                buf.a(name).a('=');

                Class<?> fieldType = field.getType();

                toString(buf, fieldType, field.get(obj));
            }

            appendVals(buf, first, addNames, addVals, addSens, addLen);

            buf.a(']');

            return buf.toString();
        }
        // Specifically catching all exceptions.
        catch (Exception e) {
            // Remove entry from cache to avoid potential memory leak
            // in case new class loader got loaded under the same identity hash.
            classCache.remove(cls.getName() + System.identityHashCode(cls.getClassLoader()));

            // No other option here.
            throw new IgniteException(e);
        }
    }