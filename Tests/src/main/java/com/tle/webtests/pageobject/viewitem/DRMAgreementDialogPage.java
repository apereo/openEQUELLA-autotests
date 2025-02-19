package com.tle.webtests.pageobject.viewitem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tle.webtests.framework.PageContext;
import com.tle.webtests.pageobject.AbstractPage;
import com.tle.webtests.pageobject.PageObject;
import com.tle.webtests.pageobject.WaitingPageObject;

public class DRMAgreementDialogPage extends AbstractPage<DRMAgreementDialogPage>
{
	@FindBy(id = "drm_licenseDialog_acceptButton")
	private WebElement acceptButton;
	@FindBy(id = "drm_licenseDialog_rejectButton")
	private WebElement rejectButton;
	@FindBy(id = "drm_licenseDialog_previewButton")
	private WebElement previewButton;

	public DRMAgreementDialogPage(PageContext context)
	{
		super(context, By.id("drm_licenseDialog_rejectButton"));
	}

	public <T extends PageObject> T preview(WaitingPageObject<T> targetPage)
	{
		previewButton.click();
		return targetPage.get();
	}

	public <T extends PageObject> T reject(WaitingPageObject<T> targetPage)
	{
		rejectButton.click();
		return targetPage.get();
	}

}
