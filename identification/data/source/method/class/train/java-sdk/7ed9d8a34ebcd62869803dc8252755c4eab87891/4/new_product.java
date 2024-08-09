public static Zone zone1() {
        return new Builder().region("z1")
                .upHttp("http://upload-z1.qiniup.com").upHttps("https://upload-z1.qiniup.com")
                .upBackupHttp("http://up-z1.qiniup.com").upBackupHttps("https://up-z1.qiniup.com")
                .iovipHttp("http://iovip-z1.qbox.me").iovipHttps("https://iovip-z1.qbox.me")
                .rsHttp("http://rs-z1.qiniu.com").rsHttps("https://rs-z1.qbox.me")
                .rsfHttp("http://rsf-z1.qiniu.com").rsfHttps("https://rsf-z1.qbox.me")
                .apiHttp("http://api-z1.qiniu.com").apiHttps("https://api-z1.qiniu.com")
                .build();
    }