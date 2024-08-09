ManualInput copy() {
        ManualInput retVal = new ManualInput();
        retVal.classifierInput = new HashMap<String, NamedTuple>(this.classifierInput);
        retVal.classifiers = new NamedTuple(this.classifiers.keys(), this.classifiers.values().toArray());
        retVal.layerInput = this.layerInput;
        retVal.sdr = Arrays.copyOf(this.sdr, this.sdr.length);
        retVal.encoding = Arrays.copyOf(this.encoding, this.encoding.length);
        retVal.activeColumns = Arrays.copyOf(this.activeColumns, this.activeColumns.length);
        retVal.sparseActives = Arrays.copyOf(this.sparseActives, this.sparseActives.length);
        retVal.previousPrediction = Arrays.copyOf(this.previousPrediction, this.previousPrediction.length);
        retVal.currentPrediction = Arrays.copyOf(this.currentPrediction, this.currentPrediction.length);
        retVal.classification = new HashMap<>(this.classification);
        retVal.anomalyScore = this.anomalyScore;
        retVal.customObject = this.customObject;
        
        return retVal;
    }