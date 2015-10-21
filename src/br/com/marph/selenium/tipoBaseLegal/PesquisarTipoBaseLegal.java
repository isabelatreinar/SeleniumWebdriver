package br.com.marph.selenium.tipoBaseLegal;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.marph.selenium.conexao.Conexao;
import br.com.marph.selenium.enums.EnumMensagens;
import br.com.marph.selenium.utils.LogUtils;

public class PesquisarTipoBaseLegal {
	private final String LOG_NAME = System.getProperty("user.name");
	private WebDriver driver;
	private Logger log = LogManager.getLogger(LOG_NAME);

	@Before
	public void startBrowser() {
		driver = new FirefoxDriver();
		Conexao.ip(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void pesquisaTipoBaseLegal() {

		LogUtils.log(EnumMensagens.INICIO, this.getClass());

		long timestart = System.currentTimeMillis();

		MenuTipoBaseLegalTemplate.prepararAcessoTipoBaseLegal(driver);

		pesquisar(driver);

		float tempoGasto = (System.currentTimeMillis() - timestart);
		float tempoSegundos = tempoGasto / 1000;

		StringBuilder sb = new StringBuilder();
		sb.append("Entrada no sistema - ").append(tempoSegundos).append(" segundos - FINALIZADO COM SUCESSO\n");

		if (tempoSegundos > 5000) {
			log.warn(sb.toString() + "\n");
		} else {
			log.info(sb.toString() + "\n");
		}
	}

	public static void pesquisar(WebDriver driver) {
		WebElement nome = driver.findElement(By.id("nome"));
		nome.sendKeys("testeeru");

		WebElement pesqAvancada = driver.findElement(By.id("btnExpandirPesquisaAvancada"));
		pesqAvancada.click();

		WebElement transfRecursos = driver.findElement(By.id("transferenciaRecursosFinanceiros_chosen"));
		transfRecursos.click();
		WebElement transSeleciona = driver.findElement(By.xpath("//*[@id='transferenciaRecursosFinanceiros_chosen']/div/div/input"));
		transSeleciona.sendKeys("Sim");
		transSeleciona.sendKeys(Keys.TAB);
		
		WebElement prestacaoMetas = driver.findElement(By.id("prestacaoMetas_chosen"));
		prestacaoMetas.click();
		WebElement prestacaoMetasSeleciona = driver
				.findElement(By.xpath("//*[@id='prestacaoMetas_chosen']/div/div/input"));
		prestacaoMetasSeleciona.sendKeys("Sim");
		prestacaoMetasSeleciona.sendKeys(Keys.TAB);

		WebElement prestacaoContas = driver.findElement(By.id("prestacaoContas_chosen"));
		prestacaoContas.click();
		WebElement prestacaoContasSeleciona = driver
				.findElement(By.xpath("//*[@id='prestacaoContas_chosen']/div/div/input"));
		prestacaoContasSeleciona.sendKeys("Sim");
		prestacaoContasSeleciona.sendKeys(Keys.TAB);

		WebElement btnPesquisar = driver.findElement(By.id("btnPesquisar"));
		btnPesquisar.click();

	}
}