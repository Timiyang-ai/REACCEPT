public final byte[] subData(int start, int length)
    {
        if (length < 1 || start >= this.length)
        {
            return P_Const.EMPTY_BYTE_ARRAY;
        }
        length = Math.min(length, this.length - start);
        byte[] subdata = newArray(length);
        System.arraycopy(buffer, start, subdata, 0, length);
        return subdata;
    }