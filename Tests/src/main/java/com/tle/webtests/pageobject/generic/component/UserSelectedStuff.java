package com.tle.webtests.pageobject.generic.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tle.webtests.framework.PageContext;

public class UserSelectedStuff extends AbstractSelectedStuff<String, UserSelectedStuff>
{
	public UserSelectedStuff(PageContext context, By parentElement)
	{
		super(context, parentElement);
	}

	@Override
	protected String getSelection(WebElement we)
	{
		return we.findElement(By.tagName("span")).getAttribute("title");
	}

	@Override
	protected String getAdditionalNameXpathConstraint(String selection)
	{
		return "[span[@title= " + quoteXPath(selection) + "]]";
	}
}
