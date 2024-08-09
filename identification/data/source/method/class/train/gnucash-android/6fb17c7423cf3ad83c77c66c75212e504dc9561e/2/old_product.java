public void setSmallestFraction(int smallestFraction) {
        if (smallestFraction != 1 && smallestFraction != 10 && smallestFraction != 100 && smallestFraction != 1000 && smallestFraction != 10000) //make sure we are not getting digits
            this.mSmallestFraction = 100;
        else
            this.mSmallestFraction = smallestFraction;
    }