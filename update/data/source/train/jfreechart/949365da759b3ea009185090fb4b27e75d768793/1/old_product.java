@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChartRenderingInfo)) {
            return false;
        }
        ChartRenderingInfo that = (ChartRenderingInfo) obj;
        if (!ObjectUtilities.equal(this.chartArea, that.chartArea)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.plotInfo, that.plotInfo)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.entities, that.entities)) {
            return false;
        }
        return true;
    }