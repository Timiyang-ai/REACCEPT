public static Zone zone1() {
        return new Builder().region("z1").upHttp("http://up-z1.qiniu.com").upHttps("https://up-z1.qbox.me").
                upBackupHttp("http://upload-z1.qiniu.com").upBackupHttps("https://upload-z1.qbox.me").
                iovipHttp("http://iovip-z1.qbox.me").iovipHttps("https://iovip-z1.qbox.me").
                rsHttp("http://rs-z1.qiniu.com").rsHttps("https://rs-z1.qbox.me")
                .rsfHttp("http://rsf-z1.qiniu.com").rsfHttps("https://rsf-z1.qbox.me")
                .apiHttp("http://api-z1.qiniu.com").apiHttps("https://api-z1.qiniu.com").build();
    }