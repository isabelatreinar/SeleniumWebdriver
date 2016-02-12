package br.com.marph.selenium.base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.marph.selenium.conexao.Conexao;
import br.com.marph.selenium.enums.EnumMensagens;
import br.com.marph.selenium.exceptions.TesteAutomatizadoException;
import br.com.marph.selenium.utils.LogUtils;

public class EditarBaseLegalMozilla {
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
	public void realizaCadastro() throws Exception {

		LogUtils.log(EnumMensagens.INICIO, this.getClass());

		long timestart = System.currentTimeMillis();

		MenuBaseLegalTemplate.prepararAcessoBaseLegal(driver);

		PesquisarBaseLegal.pesquisar(driver);

		VisualizarBaseLegal.visualizar(driver);

		edicaoCampos();

		if (driver.findElement(By.xpath("//ol[@class='breadcrumb']")).getText()
				.equalsIgnoreCase("Você está em: Base Legal > Visualizar Base Legal > Editar Base Legal")) {
			validar();
		}

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

	private void edicaoCampos() throws TesteAutomatizadoException {

		// editar
		driver.findElement(By.id("btnEditar1")).click();

		// tipoBase
		driver.findElement(By.id("tipoBaseLegal_chosen")).click();
		driver.findElement(By.xpath("//*[@id='tipoBaseLegal_chosen']/div/div/input")).sendKeys("Deliberação");
		driver.findElement(By.xpath("//*[@id='tipoBaseLegal_chosen']/div/div/input")).sendKeys(Keys.TAB);

		// numero
		driver.findElement(By.id("numero")).clear();
		driver.findElement(By.id("numero")).sendKeys("789789");

		// data
		driver.findElement(By.id("dataPublicacao")).clear();
		driver.findElement(By.id("dataPublicacao")).sendKeys("-12022016");

		File arquivo = new File("./data/TESTEEE.pdf");

		// pdf
		driver.findElement(By.id("textoPublicado")).sendKeys(arquivo.getAbsolutePath());

		// ano vigencia
		driver.findElement(By.id("dataVigencia_chosen")).click();
		driver.findElement(By.xpath("//*[@id='dataVigencia_chosen']/div/div/input")).sendKeys("2017");
		driver.findElement(By.xpath("//*[@id='dataVigencia_chosen']/div/div/input")).sendKeys(Keys.TAB);

		// salvar
		driver.findElement(By.id("btnSalvar")).click();
		// FIM CADASTRO
	}

	private void validar() throws TesteAutomatizadoException {

		if (driver.findElement(By.id("tipoBaseLegal_chosen")).getText().equalsIgnoreCase("Tipo")) {
			throw new TesteAutomatizadoException(EnumMensagens.TIPO_EM_BRANCO, this.getClass());
		} else if (StringUtils.isBlank(driver.findElement(By.id("numero")).getAttribute("value"))) {
			throw new TesteAutomatizadoException(EnumMensagens.NUMERO_EM_BRANCO, this.getClass());
		} else if (StringUtils.isBlank(driver.findElement(By.id("dataPublicacao")).getAttribute("value"))) {
			throw new TesteAutomatizadoException(EnumMensagens.DATA_EM_BRANCO, this.getClass());
		} else if (StringUtils.isBlank(driver.findElement(By.id("textoPublicado")).getAttribute("value"))) {
			throw new TesteAutomatizadoException(EnumMensagens.PDF_EM_BRANCO, this.getClass());
		}
		if (driver.findElement(By.id("dataVigencia_chosen")).getText().equalsIgnoreCase("Ano do início da vigência")) {
			throw new TesteAutomatizadoException(EnumMensagens.DATA_VIGENCIA_EM_BRANCO, this.getClass());
		} else
			throw new TesteAutomatizadoException(EnumMensagens.BASE_LEGAL_JA_CADASTRADA, this.getClass());
	}
}
