public void compute(Connections c, int[] inputVector, int[] activeArray, boolean learn) {
        if(inputVector.length != c.getNumInputs()) {
            throw new IllegalArgumentException("Input array must be same size as the defined number of inputs");
        }
        
        updateBookeepingVars(c, learn);
        calculateOverlap(c, inputVector);
    }