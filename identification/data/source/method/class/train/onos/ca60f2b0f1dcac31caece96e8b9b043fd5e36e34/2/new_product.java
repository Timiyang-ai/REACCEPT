public ArrayNode encode(Iterable<T> entities, CodecContext context) {
        ArrayNode result = context.mapper().createArrayNode();
        for (T entity : entities) {
            result.add(encode(entity, context));
        }
        return result;
    }