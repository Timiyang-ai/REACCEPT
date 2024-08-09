ManualInput copy() {
        ManualInput retVal = new ManualInput();
        retVal.classifierInput = new HashMap<String, NamedTuple>(this.classifierInput);
        retVal.classifiers = new NamedTuple(this.classifiers.keys(), this.classifiers.values().toArray());
        retVal.layerInput = this.layerInput;
        retVal.sdr = Arrays.copyOf(this.sdr, this.sdr.length);
        retVal.encoding = Arrays.copyOf(this.encoding, this.encoding.length);
        retVal.feedForwardActiveColumns = Arrays.copyOf(this.feedForwardActiveColumns, this.feedForwardActiveColumns.length);
        retVal.feedForwardSparseActives = Arrays.copyOf(this.feedForwardSparseActives, this.feedForwardSparseActives.length);
        retVal.previousPredictiveCells = new LinkedHashSet<Cell>(this.previousPredictiveCells);
        retVal.predictiveCells = new LinkedHashSet<Cell>(this.predictiveCells);
        retVal.classification = new HashMap<>(this.classification);
        retVal.anomalyScore = this.anomalyScore;
        retVal.customObject = this.customObject;
        retVal.computeCycle = this.computeCycle;
        retVal.activeCells = new LinkedHashSet<Cell>(this.activeCells);
        
        return retVal;
    }