public Node appendColumn(Node tableNode, TableColumn column) {
        Document document = tableNode.getOwnerDocument();
        Node columnNode = document.createElement(COLUMN);
        tableNode.appendChild(columnNode);

        DOMUtil.appendAttribute(columnNode, "id", String.valueOf(column.getId()));
        DOMUtil.appendAttribute(columnNode, "name", column.getName());
        DOMUtil.appendAttribute(columnNode, "type", column.getTypeName());
        DOMUtil.appendAttribute(columnNode, "size", String.valueOf(column.getLength()));
        DOMUtil.appendAttribute(columnNode, "digits", String.valueOf(column.getDecimalDigits()));
        DOMUtil.appendAttribute(columnNode, "nullable", String.valueOf(column.isNullable()));
        DOMUtil.appendAttribute(columnNode, "autoUpdated", String.valueOf(column.isAutoUpdated()));
        if (column.getDefaultValue() != null) {
            String defaultValue = column.getDefaultValue().toString();
            if (isBinary(defaultValue)) {
                // we're run into a binary default value, convert it to its hex equivalent
                defaultValue = asBinary(defaultValue);
                // and indicate that it's been converted
                DOMUtil.appendAttribute(columnNode, "defaultValueIsBinary", "true");
            }
            DOMUtil.appendAttribute(columnNode, "defaultValue", defaultValue);
        }
        DOMUtil.appendAttribute(columnNode, "remarks", column.getComments() == null ? "" : column.getComments());

        for (TableColumn childColumn : column.getChildren()) {
            Node childNode = document.createElement("child");
            columnNode.appendChild(childNode);
            appendForeignKeyAttributes(childNode, childColumn, column.getChildConstraint(childColumn));
        }

        for (TableColumn parentColumn : column.getParents()) {
            Node parentNode = document.createElement("parent");
            columnNode.appendChild(parentNode);
            appendForeignKeyAttributes(parentNode, parentColumn, column.getParentConstraint(parentColumn));
        }

        return columnNode;
    }