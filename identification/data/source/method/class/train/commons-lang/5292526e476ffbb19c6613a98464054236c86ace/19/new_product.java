public StrBuilder appendWithSeparators(final Iterable<?> iterable, String separator) {
        if (iterable != null) {
            separator = ObjectUtils.toString(separator);
            final Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                append(it.next());
                if (it.hasNext()) {
                    append(separator);
                }
            }
        }
        return this;
    }