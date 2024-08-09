@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractCategoryItemRenderer)) {
            return false;
        }
        AbstractCategoryItemRenderer that = (AbstractCategoryItemRenderer) obj;

        if (!ObjectUtils.equal(this.itemLabelGeneratorMap,
                that.itemLabelGeneratorMap)) {
            return false;
        }
        if (!ObjectUtils.equal(this.defaultItemLabelGenerator,
                that.defaultItemLabelGenerator)) {
            return false;
        }
        if (!ObjectUtils.equal(this.toolTipGeneratorMap,
                that.toolTipGeneratorMap)) {
            return false;
        }
        if (!ObjectUtils.equal(this.defaultToolTipGenerator,
                that.defaultToolTipGenerator)) {
            return false;
        }
        if (!ObjectUtils.equal(this.itemURLGeneratorMap,
                that.itemURLGeneratorMap)) {
            return false;
        }
        if (!ObjectUtils.equal(this.defaultItemURLGenerator,
                that.defaultItemURLGenerator)) {
            return false;
        }
        if (!ObjectUtils.equal(this.legendItemLabelGenerator,
                that.legendItemLabelGenerator)) {
            return false;
        }
        if (!ObjectUtils.equal(this.legendItemToolTipGenerator,
                that.legendItemToolTipGenerator)) {
            return false;
        }
        if (!ObjectUtils.equal(this.legendItemURLGenerator,
                that.legendItemURLGenerator)) {
            return false;
        }
        return super.equals(obj);
    }