@Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LineAndShapeRenderer)) {
            return false;
        }

        LineAndShapeRenderer that = (LineAndShapeRenderer) obj;
        if (this.defaultLinesVisible != that.defaultLinesVisible) {
            return false;
        }
        if (!ObjectUtils.equal(this.seriesLinesVisible,
                that.seriesLinesVisible)) {
            return false;
        }
        if (this.defaultShapesVisible != that.defaultShapesVisible) {
            return false;
        }
        if (!ObjectUtils.equal(this.seriesShapesVisible,
                that.seriesShapesVisible)) {
            return false;
        }
        if (!ObjectUtils.equal(this.seriesShapesFilled,
                that.seriesShapesFilled)) {
            return false;
        }
        if (this.defaultShapesFilled != that.defaultShapesFilled) {
            return false;
        }
        if (this.useOutlinePaint != that.useOutlinePaint) {
            return false;
        }
        if (this.useSeriesOffset != that.useSeriesOffset) {
            return false;
        }
        if (this.itemMargin != that.itemMargin) {
            return false;
        }
        return super.equals(obj);
    }