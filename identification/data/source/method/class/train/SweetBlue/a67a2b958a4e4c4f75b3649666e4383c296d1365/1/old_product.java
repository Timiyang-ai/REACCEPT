public final byte[] subData(int start, int length)
    {
        length = Math.min(length, this.length - start);
        byte[] subdata = newArray(length);
        System.arraycopy(buffer, start, subdata, 0, length);
        return subdata;
    }