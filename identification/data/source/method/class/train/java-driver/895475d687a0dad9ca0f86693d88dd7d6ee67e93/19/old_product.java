public Select.Selection select() {
        // Note: the fact we return Select.Selection as return type is on purpose.
        return new Select.SelectionOrAlias(cluster);
    }