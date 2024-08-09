public static void addEvaluation(Evaluation eval) {
		clickEvaluationTab();
		// Select the course
		waitAndClick(inputCourseID);
		waitAndClick(By.xpath("//option[@value='" + eval.courseID + "']"));
		// Fill in the evaluation name
		wdFillString(inputEvaluationName, eval.name);
		// Allow P2P comment
		wdClick(By.xpath("//*[@id='commentsstatus'][@value='"
				+ eval.p2pcomments + "']"));
		// Fill in instructions
		wdFillString(inputInstruction, eval.instructions);
		// Select deadline date
		wdClick(inputClosingDate);
		selenium.waitForPopUp("window_deadline", "30000");
		selenium.selectWindow("name=window_deadline");
		wdClick(By.xpath("//a[contains(@href, '" + eval.dateValue + "')]"));
		for (String s : driver.getWindowHandles()) {
			selenium.selectWindow(s);
			break;
		}
		selectDropdownByValue(inputClosingTime, eval.nextTimeValue);
		// Select grace period
		selectDropdownByValue(inputGracePeriod,
				Integer.toString(eval.gracePeriod));
		// Submit the form
		wdClick(addEvaluationButton);
	}