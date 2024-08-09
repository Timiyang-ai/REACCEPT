public ArrayNode encode(Iterable<T> entities, ObjectMapper mapper) {
        ArrayNode result = mapper.createArrayNode();
        for (T entity : entities) {
            result.add(encode(entity, mapper));
        }
        return result;
    }