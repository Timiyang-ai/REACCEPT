@RequestLine("POST")
    @Body("<v01:deleteResourceRecord><transactionID /><guid>{guid}</guid></v01:deleteResourceRecord>")
    void deleteRecord(@Named("guid") String guid);