	@Test
	public void timerTest(){
		TimeInterval timer = DateUtil.timer();
		
		//---------------------------------
		//-------这是执行过程
		//---------------------------------
		
		timer.interval();//花费毫秒数
		timer.intervalRestart();//返回花费时间，并重置开始时间
		timer.intervalMinute();//花费分钟数
	}