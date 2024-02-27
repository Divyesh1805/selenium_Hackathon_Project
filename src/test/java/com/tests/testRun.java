package com.tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					features= {".//Features/"},
					glue="stepDefinitions",
					plugin= {"pretty", "html:Reports/myreport.html",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",		
					},
					dryRun=false,
					monochrome=true,
					publish=true
		)
public class testRun {

}
