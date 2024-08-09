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

            if (writeModification.transformedItem() != null) {
                transformedItem.set(writeModification.transformedItem());
            }

            if (writeModification.additionalConditionalExpression() != null) {
                if (conditionalExpression.get() == null) {
                    conditionalExpression.set(writeModification.additionalConditionalExpression());
                } else {
                    conditionalExpression.set(
                        Expression.coalesce(conditionalExpression.get(),
                                            writeModification.additionalConditionalExpression(),
                                            " AND "));
                }
            }
        });

        return WriteModification.builder()
                                .transformedItem(transformedItem.get())
                                .additionalConditionalExpression(conditionalExpression.get())
                                .build();
    }