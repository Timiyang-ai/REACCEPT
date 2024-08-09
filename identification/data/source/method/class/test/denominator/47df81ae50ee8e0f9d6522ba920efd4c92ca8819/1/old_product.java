@RequestLine("POST")
    @Body("<v01:deleteLBPool><transactionID /><lbPoolID>{lbPoolID}</lbPoolID><DeleteAll>Yes</DeleteAll><retainRecordId /></v01:deleteLBPool>")
    void deleteRRPool(@Named("lbPoolID") String id);