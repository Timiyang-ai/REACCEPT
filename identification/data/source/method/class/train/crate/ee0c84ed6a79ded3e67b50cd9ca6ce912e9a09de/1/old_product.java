public Window merge(Window that) {
        if (this.empty()) {
            return that;
        }

        final List<Expression> partitionBy;
        if (!this.partitions.isEmpty()) {
            throw new IllegalArgumentException(
                "Cannot override PARTITION BY clause of window " + this.windowRef);
        } else {
            partitionBy = that.getPartitions();
        }

        final List<SortItem> orderBy;
        if (that.getOrderBy().isEmpty()) {
            orderBy = this.getOrderBy();
        } else {
            if (!this.getOrderBy().isEmpty()) {
                throw new IllegalArgumentException(
                    "Cannot override ORDER BY clause of window " + this.windowRef);
            }
            orderBy = that.getOrderBy();
        }

        if (that.getWindowFrame().isPresent()) {
            throw new IllegalArgumentException(
                "Cannot copy window " + this.windowRef() + " because it has a frame clause");
        }

        return new Window(that.windowRef, partitionBy, orderBy, this.getWindowFrame());
    }