package filpkartPagewiseClass;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import flipkartUtil.WebUtil;

public class FlipkartHomePage {

//	//=============================================================================================	
	private WebUtil commonmethod;

	 public FlipkartHomePage(WebUtil wu) {

		PageFactory.initElements(wu.getWebDriver(), this);
		commonmethod=wu;
	}
    @FindBy(css = "._2KpZ6l._2doB4z")
    private WebElement login_popup;

    public void closeLoginPopup() {
        login_popup.click();
    }

    @FindBy(xpath = "//div[@class='_1bEAQy _2iN8uD']//img[@class='_2OHU_q aA9eLq' and @alt='Sofas']")
    private WebElement sofasBanner;

    public void clickOnSofasBanner() {
    	commonmethod. waitForElementVisibility(sofasBanner);
    	commonmethod.waitForElementClickable(sofasBanner);
    	commonmethod.jsClick(sofasBanner,"click on sofas banner");
    }

    @FindBy(css = "._1kidPb span._2I9KP_:nth-child(1)")
    private WebElement electronicsLnk;

    public void hoverOnElectronicsLnk() {
    	commonmethod.hoverOnElectronicsLnk(electronicsLnk);
    }

    @FindBy(css = "._1fwVde:nth-child(1) a._3QN6WI._1MMnri._32YDvl~a")
    private List<WebElement> mobileBrands;

    public List getMobileBrands() {
        String txt = null;
        List list = new ArrayList();
        for (WebElement w : mobileBrands) {
            txt = w.getText();
            list.add(txt);
        }
        return list;
    }
	}


