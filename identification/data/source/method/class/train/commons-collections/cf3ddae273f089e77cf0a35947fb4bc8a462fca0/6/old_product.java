public static Collection collect(Iterator inputIterator, final Transformer transformer, final Collection outputCollection) {
        if (inputIterator != null && transformer != null) {
            while (inputIterator.hasNext()) {
                Object item = inputIterator.next();
                Object value = transformer.transform(item);
                outputCollection.add(value);
            }
        }
        return outputCollection;
    }