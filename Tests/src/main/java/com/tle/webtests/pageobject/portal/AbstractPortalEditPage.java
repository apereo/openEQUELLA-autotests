package com.tle.webtests.pageobject.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tle.webtests.framework.PageContext;
import com.tle.webtests.pageobject.AbstractPage;
import com.tle.webtests.pageobject.ExpectWaiter;
import com.tle.webtests.pageobject.ExpectedConditions2;
import com.tle.webtests.pageobject.PrefixedName;
import com.tle.webtests.pageobject.WaitingPageObject;
import com.tle.webtests.pageobject.generic.component.MultiLingualEditbox;

public abstract class AbstractPortalEditPage<T extends AbstractPortalEditPage<T>> extends AbstractPage<T>
{
	private WebElement getDisableCheck()
	{
		return findById("_d");
	}
	private WebElement getSave()
	{
		return findById("_sv");
	}
	private WebElement getOthersCheck()
	{
		return findById("_i");
	}
	private WebElement getMinCheck()
	{
		return findById("_m");
	}

	private By getCloseCheckBy()
	{
		return byPrefixId(getId(), "_c");
	}

	private WebElement getCloseCheck()
	{
		return find(driver, getCloseCheckBy());
	}

	private WebElement findById(String postfix)
	{
		return driver.findElement(By.id(getId()+postfix));
	}

	private WebElement getViewExpressionInput()
	{
		return find(driver, By.name(getId()+"_selector_es.e"));
	}

	public AbstractPortalEditPage(PageContext context)
	{
		super(context, By.xpath("//div[normalize-space(@class)='portletedit']"));
	}

	public AbstractPortalEditPage(PageContext context, By loadedBy)
	{
		super(context, loadedBy);
	}

	public <P extends AbstractPage<P>> P save(P page)
	{
		getSave().click();
		return page.get();
	}

	public T setTitle(String title)
	{
		getTitleSection().setCurrentString(title);
		return get();
	}

	public T setTitle(PrefixedName title)
	{
		getTitleSection().setCurrentString(title.toString());
		return get();
	}

	public MultiLingualEditbox getTitleSection()
	{
		return new MultiLingualEditbox(context, getId() + "_t").get();
	}

	private void check(WebElement check, boolean checked)
	{
		if( check.isSelected() != checked )
		{
			check.click();
		}
	}

	public T setDisabled(boolean checked)
	{
		check(getDisableCheck(), checked);
		return get();
	}

	public T setShowForOthers(boolean checked)
	{
		if( getOthersCheck().isSelected() != checked )
		{
			WaitingPageObject<T> aWaiter;
			if( checked )
			{
				aWaiter = visibilityWaiter(driver, getCloseCheckBy());
			}
			else
			{
				aWaiter = removalWaiter(getCloseCheck());
			}

			getOthersCheck().click();
			return aWaiter.get();
		}
		return get();
	}

	public T setUsersCanClose(boolean checked)
	{
		setShowForOthers(true);
		check(getCloseCheck(), checked);
		return get();
	}

	public T setUsersCanMin(boolean checked)
	{
		setShowForOthers(true);
		check(getMinCheck(), checked);
		return get();
	}

	public T showForAll()
	{
		return showForExpression("* ");
	}

	public T showForExpression(String expression)
	{
		setShowForOthers(true);
		((JavascriptExecutor) driver).executeScript("_subev('" + getId() + ".expression', '', '" + expression + "');");

		return ExpectWaiter.waiter(ExpectedConditions2.elementAttributeToBe(getViewExpressionInput(), "value", expression),
			this).get();
	}

	abstract public String getType();

	abstract public String getId();
}
