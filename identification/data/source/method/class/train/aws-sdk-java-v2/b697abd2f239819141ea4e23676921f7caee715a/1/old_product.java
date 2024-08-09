@Override
    public WriteModification beforeWrite(Map<String, AttributeValue> item,
                                         OperationContext operationContext,
                                         TableMetadata tableMetadata) {
        AtomicReference<Map<String, AttributeValue>> transformedItem = new AtomicReference<>();
        AtomicReference<Expression> conditionalExpression = new AtomicReference<>();

        this.extensionChain.forEach(extension -> {
            Map<String, AttributeValue> itemToTransform = transformedItem.get() == null ? item : transformedItem.get();
            WriteModification writeModification = extension.beforeWrite(itemToTransform,
                                                                        operationContext,
                                                                        tableMetadata);

            if (writeModification.getTransformedItem() != null) {
                transformedItem.set(writeModification.getTransformedItem());
            }

            if (writeModification.getAdditionalConditionalExpression() != null) {
                if (conditionalExpression.get() == null) {
                    conditionalExpression.set(writeModification.getAdditionalConditionalExpression());
                } else {
                    conditionalExpression.set(
                        Expression.coalesce(conditionalExpression.get(),
                                            writeModification.getAdditionalConditionalExpression(),
                                            " AND "));
                }
            }
        });

        return WriteModification.builder()
                                .transformedItem(transformedItem.get())
                                .additionalConditionalExpression(conditionalExpression.get())
                                .build();
    }