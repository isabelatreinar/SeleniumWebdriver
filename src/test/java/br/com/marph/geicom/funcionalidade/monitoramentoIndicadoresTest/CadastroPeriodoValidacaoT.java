package br.com.marph.geicom.funcionalidade.monitoramentoIndicadoresTest;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.marph.geicom.funcionalidade.monitoramentoIndicadoresPage.PesquisaTelaMonitoramentoP;
import br.com.marph.geicom.funcionalidade.monitoramentoIndicadoresPage.FormularioCadastroPeriodoValidacaoP;
import br.com.marph.geicom.util.AcessoUtils;
import br.com.marph.geicom.util.IConstante;
import br.com.marph.geicom.util.Menus;

public class CadastroPeriodoValidacaoT {

	private WebDriver driver;
	private PesquisaTelaMonitoramentoP pesquisaMonitoramento;
	FormularioCadastroPeriodoValidacaoP periodoValidacao;

	@Before
	public void startUp() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void cadastrarPeriodoMonitoramento() throws InterruptedException{
		
		driver.get(IConstante.Url.LOGIN);
		AcessoUtils.acessarSistemaAdmin(driver);
		
		Menus.MenuProcessos(driver);
		Menus.subMenuMonitoramento(driver);
		
		pesquisaMonitoramento = new PesquisaTelaMonitoramentoP(driver);
		pesquisaMonitoramento.pesquisar();
		
		periodoValidacao = new FormularioCadastroPeriodoValidacaoP(driver);
		periodoValidacao.preencherCadastro();
		
	}
}
