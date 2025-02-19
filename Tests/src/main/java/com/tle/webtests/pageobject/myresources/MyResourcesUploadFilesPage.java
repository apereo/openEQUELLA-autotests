package com.tle.webtests.pageobject.myresources;

import com.dytech.common.legacyio.FileUtils;
import com.tle.common.PathUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tle.webtests.pageobject.AbstractPage;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyResourcesUploadFilesPage extends AbstractPage<MyResourcesUploadFilesPage>
{
	private static final String REPLACE_FILE_INPUT_ID = "myr_f";
	private static final String UPLOAD_FILE_INPUT_ID = "myr_s";
	private static final String DESCRIPTION_INPUT_ID = "myr_d";
	private static final String SEARCH_TAGS_INPUT_ID = "myr_dndt";
	private static final String EDIT_TAGS_INPUT_ID = "myr_t";

	@FindBy(id = REPLACE_FILE_INPUT_ID)
	private WebElement fileInput;
	@FindBy(id = UPLOAD_FILE_INPUT_ID)
	private WebElement saveButton;
	@FindBy(id = DESCRIPTION_INPUT_ID)
	private WebElement descriptionField;
	@FindBy(id = SEARCH_TAGS_INPUT_ID)
	private WebElement tagField;
	@FindBy(id = EDIT_TAGS_INPUT_ID)
	private WebElement editTagField;
	@FindBy(id = "myr_c")
	private WebElement cancelButton;
	@FindBy(id = "myr_fm_file")
	private WebElement uploadElem;

	private MyResourcesPage myResourcesPage;

	@FindBy(id = "")
	private WebElement dndArchiveOption;
	@FindBy(id = "filedndarea")
	private WebElement dndDropZone;

	public MyResourcesUploadFilesPage(MyResourcesPage myResourcesPage)
	{
		super(myResourcesPage.getContext());
		this.myResourcesPage = myResourcesPage;
	}

	@Override
	protected WebElement findLoadedElement()
	{
		return cancelButton;
	}

	public MyResourcesPage uploadFile(String path, String description, String tags)
	{
		setSearchTags(tags);
		uploadElem.sendKeys(path);
		getWaiter().until(ExpectedConditions.visibilityOfElementLocated(new ByChained(By.id("dndfiles"),By.className("complete"))));
		cancelButton.click();
		MyResourcesPage searchPage = new MyResourcesPage(context, "scrapbook").get();
		String fileOnly = PathUtils.getFilenameFromFilepath(path);
		MyResourcesUploadFilesPage edit = searchPage.exactQuery(fileOnly).getResultForTitle(fileOnly).get()
				.clickAction("Edit", new MyResourcesUploadFilesPage(searchPage));
		edit.setDescription(description);
		return edit.save();
	}

	public boolean hasArchiveOption(String option)
	{
		try
		{
			String xpath = "//ul[@class='dropdown-menu']//li//a[contains(text(), '" + option + "')]";
			driver.findElement(By.xpath(xpath));
		}
		catch( NotFoundException ex )
		{
			return false;
		}
		return true;
	}

	public boolean hasDndTagField()
	{
		try
		{
			String xpath = "//input[@id='myr_dndt']";
			driver.findElement(By.xpath(xpath));
		}
		catch( NotFoundException ex )
		{
			return false;
		}
		return true;
	}

	public boolean hasDndDropZone()
	{
		try
		{
			driver.findElement(By.cssSelector(".filedrop"));
		}
		catch( NotFoundException ex )
		{
			return false;
		}
		return true;
	}

	public void setSearchTags(String tags)
	{
		tagField.clear();
		tagField.sendKeys(tags);
	}

	public void setDescription(String description)
	{
		descriptionField.clear();
		descriptionField.sendKeys(description);
	}

	public MyResourcesPage cancel()
	{
		cancelButton.click();
		return myResourcesPage.get();
	}

	public MyResourcesPage save()
	{
		saveButton.click();
		return myResourcesPage.get();
	}

	public void editSearchTags(String tags)
	{
		editTagField.clear();
		editTagField.sendKeys(tags);
	}
}
