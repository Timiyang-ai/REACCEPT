public Object clone() throws CloneNotSupportedException {
        try {
            DoubleData data = (DoubleData) super.clone();
            data.sampleRate = sampleRate;
            data.collectTime = collectTime;
            data.firstSampleNumber = firstSampleNumber;
            return data;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }