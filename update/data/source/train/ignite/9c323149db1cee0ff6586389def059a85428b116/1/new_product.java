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

                buf.a(fd.getName()).a('=');

                switch (fd.type()) {
                    case GridToStringFieldDescriptor.FIELD_TYPE_OBJECT:
                        toString(buf, fd.fieldClass(), GridUnsafe.getObjectField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_BYTE:
                        buf.a(GridUnsafe.getByteField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_BOOLEAN:
                        buf.a(GridUnsafe.getBooleanField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_CHAR:
                        buf.a(GridUnsafe.getCharField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_SHORT:
                        buf.a(GridUnsafe.getShortField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_INT:
                        buf.a(GridUnsafe.getIntField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_FLOAT:
                        buf.a(GridUnsafe.getFloatField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_LONG:
                        buf.a(GridUnsafe.getLongField(obj, fd.offset()));

                        break;
                    case GridToStringFieldDescriptor.FIELD_TYPE_DOUBLE:
                        buf.a(GridUnsafe.getDoubleField(obj, fd.offset()));

                        break;
                }
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