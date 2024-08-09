    private JsonValue render(Column column, Object value) {
        return AbstractRenderer.encodeValue(value, column.getRenderer(),
                column.getConverter(), grid.getLocale());
    }