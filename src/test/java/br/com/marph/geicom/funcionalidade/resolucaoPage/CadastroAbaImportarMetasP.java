package br.com.marph.geicom.funcionalidade.resolucaoPage;

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.marph.geicom.enums.EnumMensagemLog;
import br.com.marph.geicom.util.SeleniumUtil;

public class CadastroAbaImportarMetasP {

	private static final String BTN_PROXIMO = "btnProximo";
	private static final String BTN_IMPORTAR = "buttonImportar";
	private static final String BTN_IMPORTAR_PLANILHA = "buttonImportarPlanilha";
	private WebDriver driver;
	private SeleniumUtil seleniumUtil;

	public CadastroAbaImportarMetasP(WebDriver driver) {
		this.driver = driver;
	}

	public void importarMetas() throws InterruptedException {

		seleniumUtil = SeleniumUtil.getInstance();

		seleniumUtil.clickElementId(driver, BTN_IMPORTAR_PLANILHA);

		File planilha = new File("./data/Geicom/importacaoMetasExport.xlsx");

		if (planilha.exists()) {
			WebElement selecionarPlanilha = driver.findElement(By.id("uploadMetasPactuadas"));
			selecionarPlanilha.sendKeys(planilha.getAbsolutePath());
		} else {
			Assert.fail(EnumMensagemLog.ARQUIVO_NÃO_ENCONTRADO.getMensagem());
		}

		seleniumUtil.clickElementId(driver, BTN_IMPORTAR);
		Thread.sleep(5000);

		seleniumUtil.clickElementId(driver, BTN_PROXIMO);

	}
}
