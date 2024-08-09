public <Item extends IItem> Item getItem(int position) {
        return (Item) getInternalItem(position).item;
    }