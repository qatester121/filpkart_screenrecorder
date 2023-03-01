package com.flipkartAutomation.Validation;

import java.util.List;

import org.testng.annotations.Test;

import filpkartPagewiseClass.FlipkartHomePage;
import flipkartUtil.Testbaseclass;
import flipkartUtil.WebUtil;

public class FlipkartRunner extends Testbaseclass {
     @Test
	public void flipkartValidation() throws Exception {
    	 WebUtil web= new WebUtil();
		FlipkartHomePage fkobj=	new FlipkartHomePage(util);
		
		fkobj.closeLoginPopup();
		fkobj.clickOnSofasBanner();
		fkobj.hoverOnElectronicsLnk();
	        List<String> s = fkobj.getMobileBrands();
	        for (int i = 0; i < s.size(); i++) {
	        System.out.println(s.get(i));

	}
}
