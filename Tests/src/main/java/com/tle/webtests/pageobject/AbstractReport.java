package com.tle.webtests.pageobject;

import org.openqa.selenium.By;

import com.tle.webtests.framework.PageContext;
import com.tle.webtests.pageobject.reporting.AbstractReportWindow;

public class AbstractReport<T extends AbstractPage<T>> extends AbstractPage<T>
{
	private AbstractReportWindow<?, ?> reportWindow;

	public AbstractReport(PageContext context, By by)
	{
		super(context, by, 120);
	}

	public AbstractReportWindow<?, ?> getReportWindow()
	{
		return reportWindow;
	}

	public void setReportWindow(AbstractReportWindow<?, ?> reportWindow)
	{
		this.reportWindow = reportWindow;
	}
	
	public void close()
	{
		reportWindow.close();
	}
		
}
