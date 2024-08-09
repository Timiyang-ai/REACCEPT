    @Test
    public void createAccount() {

        Result<List<Account>> result = this.accountService.createAccount(0, null);
        assertTrue(result.isFailed());
        assertNotNull(result.getMsg());

        //无密码时
        result = this.accountService.createAccount(1, null);
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(result.getData().size(), 1);
        // 比较账户和从数据库中查出来的账户是否一致

        result = this.accountService.createAccount(5, null);
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(result.getData().size(), 5);
        // 比较账户和从数据库中查出来的账户是否一致

        //测试最大一次生成账户数量
        result = this.accountService.createAccount(10000, null);
        assertTrue(result.isFailed());
        assertNotNull(result.getMsg());


        // 设置钱包密码
        result = this.accountService.createAccount(1, null);
        assertTrue(result.isSuccess());
        assertNotNull(result.getMsg());

        // 设置钱包密码为nuls123456
        result = this.accountService.createAccount(1, "nuls123456");
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(result.getData().size(), 1);
        // 比较账户和从数据库中查出来的账户是否一致


        result = this.accountService.createAccount(6, "nuls123456");
        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        assertEquals(result.getData().size(), 6);
        // 比较账户和从数据库中查出来的账户是否一致

        result = this.accountService.createAccount(10000, null);
        assertTrue(result.isFailed());
        assertNotNull(result.getMsg());

    }