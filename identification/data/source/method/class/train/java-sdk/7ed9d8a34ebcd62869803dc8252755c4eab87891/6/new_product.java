public static Zone zone1() {
        return new Builder().up("http://up-z1.qiniu.com").upBackup("http://upload-z1.qiniu.com").
                upHttps("https://up-z1.qbox.me").upBackupHttps("https://upload-z1.qbox.me").
                iovip("http://iovip-z1.qbox.me").iovipHttps("https://iovip-z1.qbox.me").
                rs("http://rs-z1.qiniu.com").rsHttps("https://rs-z1.qbox.me").
                rsf("http://rsf-z1.qiniu.com").rsfHttps("https://rsf-z1.qbox.me").
                api("http://api.qiniu.com").apiHttps("https://api.qiniu.com").build();
    }