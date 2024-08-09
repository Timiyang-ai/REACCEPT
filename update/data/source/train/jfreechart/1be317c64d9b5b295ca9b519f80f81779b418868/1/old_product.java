@Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LineAndShapeRenderer)) {
            return false;
        }

        LineAndShapeRenderer that = (LineAndShapeRenderer) obj;
        if (this.baseLinesVisible != that.baseLinesVisible) {
            return false;
        }
        if (!ObjectUtils.equal(this.seriesLinesVisible,
                that.seriesLinesVisible)) {
            return false;
        }
        if (!ObjectUtils.equal(this.linesVisible, that.linesVisible)) {
            return false;
        }
        if (this.baseShapesVisible != that.baseShapesVisible) {
            return false;
        }
        if (!ObjectUtils.equal(this.seriesShapesVisible,
                that.seriesShapesVisible)) {
            return false;
        }
        if (!ObjectUtils.equal(this.shapesVisible, that.shapesVisible)) {
            return false;
        }
        if (!ObjectUtils.equal(this.shapesFilled, that.shapesFilled)) {
            return false;
        }
        if (!ObjectUtils.equal(this.seriesShapesFilled,
                that.seriesShapesFilled)) {
            return false;
        }
        if (this.baseShapesFilled != that.baseShapesFilled) {
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