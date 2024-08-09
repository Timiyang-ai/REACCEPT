@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CategoryItemEntity)) {
            return false;
        }
        CategoryItemEntity that = (CategoryItemEntity) obj;
        if (!this.rowKey.equals(that.rowKey)) {
            return false;
        }
        if (!this.columnKey.equals(that.columnKey)) {
            return false;
        }
        if (!ObjectUtils.equal(this.dataset, that.dataset)) {
            return false;
        }

        // check the deprecated fields
        if (this.categoryIndex != that.categoryIndex) {
            return false;
        }
        if (this.series != that.series) {
            return false;
        }
        if (!ObjectUtils.equal(this.category, that.category)) {
            return false;
        }
        return super.equals(obj);
    }