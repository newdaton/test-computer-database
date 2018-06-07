package com.computer.sample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result; 

@RunWith(Suite.class)
@SuiteClasses({
	ComputerDatabaseCreateTest.class,
	ComputerDatabaseReadTest.class,
	ComputerDatabaseUpdateTest.class,
	ComputerDatabaseDeleteTest.class,
})


public class AutomatedTests {
	public static void main(String args[]) {
		  JUnitCore junit = new JUnitCore();
		  junit.addListener(new TextListener(System.out));
		  Result result = junit.run(AutomatedTests.class); // Replace "SampleTest" with the name of your class
		  if (result.getFailureCount() > 0) {
		    System.out.println("Test failed.");
		    System.exit(1);
		  } else {
		    System.out.println("Test finished successfully.");
		    System.exit(0);
		  }
		}
}
