public static void addEvaluation(Evaluation eval) {
		waitAndClick(By.className("t_evaluations"));

		// Select the course
		waitAndClick(By.id("courseid"));
		waitAndClick(By.xpath("//option[@value='" + eval.courseID + "']"));

		// Fill in the evaluation name
		wdFillString(By.id("evaluationname"), eval.name);

		// Allow P2P comment
		wdClick(By.xpath("//*[@id='commentsstatus'][@value='" + eval.p2pcomments
				+ "']"));

		// Fill in instructions
		wdFillString(By.id("instr"), eval.instructions);

		// Select deadline date
		wdClick(By.xpath("//*[@id='deadline']"));
		selenium.waitForPopUp("window_deadline", "30000");
		selenium.selectWindow("name=window_deadline");
		wdClick(By.xpath("//a[contains(@href, '" + eval.dateValue + "')]"));

		for (String s : driver.getWindowHandles()) {
			selenium.selectWindow(s);
			break;
		}

		selectDropdownByValue(By.id("deadlinetime"), eval.nextTimeValue);

		// Select grace period
		selectDropdownByValue(By.id("graceperiod"),
				Integer.toString(eval.gracePeriod));

		// Submit the form
		wdClick(By.id("t_btnAddEvaluation"));
	}