@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChartRenderingInfo)) {
            return false;
        }
        ChartRenderingInfo that = (ChartRenderingInfo) obj;
        if (!ObjectUtils.equal(this.chartArea, that.chartArea)) {
            return false;
        }
        if (!ObjectUtils.equal(this.plotInfo, that.plotInfo)) {
            return false;
        }
        if (!ObjectUtils.equal(this.entities, that.entities)) {
            return false;
        }
        return true;
    }