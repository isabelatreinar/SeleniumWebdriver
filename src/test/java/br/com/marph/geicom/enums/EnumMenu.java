package br.com.marph.geicom.enums;

public enum EnumMenu {

	RESOLUCAO("//td[@onmouseup='cmItemMouseUp (this,4)']"), 
	CADASTRO_RESOLUCAO(".//*[@id='resolucaoMenu']/td[2]");

	private String id;

	private EnumMenu(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
