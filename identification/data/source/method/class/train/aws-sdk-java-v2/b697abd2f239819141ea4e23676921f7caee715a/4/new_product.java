@Override
    public ReadModification afterRead(Map<String, AttributeValue> item,
                                      OperationContext operationContext,
                                      TableMetadata tableMetadata) {
        AtomicReference<Map<String, AttributeValue>> transformedItem = new AtomicReference<>();

        this.extensionChain.descendingIterator().forEachRemaining(extension -> {
            Map<String, AttributeValue> itemToTransform = transformedItem.get() == null ? item : transformedItem.get();
            ReadModification readModification = extension.afterRead(itemToTransform, operationContext, tableMetadata);

            if (readModification.transformedItem() != null) {
                transformedItem.set(readModification.transformedItem());
            }
        });

        return ReadModification.builder()
                               .transformedItem(transformedItem.get())
                               .build();
    }