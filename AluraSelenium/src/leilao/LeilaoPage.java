package leilao;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeilaoPage {
	
	WebDriver driver;
	
	public LeilaoPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	public void navega(String url){
		this.driver.get(url);
	}
	
	
	public NovoLeilaoPage novoLeilao(WebDriver driver){
		this.driver.findElement(By.linkText("Novo Leilão")).click();
		return new NovoLeilaoPage(driver);
	}
	
	// Métodos de tratamento de Grids

		public List<WebElement> getGridLeiloesCadastrados() {
			return driver.findElements(By.xpath("//table/tbody/tr[position()>1]"));
		}

		public List<WebElement> recuperaLinhaGrid(List<WebElement> grid, int elemento) {

			List<WebElement> linha = new ArrayList<WebElement>();

			if (!isGridVazia(grid)) {
				linha.add(0, grid.get(elemento - 1).findElement(By.xpath("td[1]")));
				linha.add(1, grid.get(elemento - 1).findElement(By.xpath("td[2]")));
				linha.add(2, grid.get(elemento - 1).findElement(By.xpath("td[3]")));
				linha.add(3, grid.get(elemento - 1).findElement(By.xpath("td[4]")));
				linha.add(4, grid.get(elemento - 1).findElement(By.xpath("td[5]")));
				linha.add(5, grid.get(elemento - 1).findElement(By.xpath("td[6]")));
			}

			return linha;
		}
		

		public Boolean isGridVazia(List<WebElement> grid) {
			Boolean flag;

			if (grid.isEmpty()) {
				flag = true;
			} else {
				flag = false;
			}

			return flag;
		}
		
		public int retornaQtdeElementosGrid(List<WebElement> grid){
			return grid.size();
		}
		
		public List<WebElement> filtraGrid(List<WebElement> grid,String nome,String dataAbertura, String valorInicial, String usuario, String usado){
			
			List<WebElement> linhaFiltrada = new ArrayList<WebElement>();
			
			if(!grid.isEmpty()){
					
				for (WebElement linha : grid) {
					
					if(linha.findElement(By.xpath("td[1]")).getText().contains(nome)){
						linhaFiltrada.add(0,linha.findElement(By.xpath("td[1]")));
						linhaFiltrada.add(1,linha.findElement(By.xpath("td[2]")));
						linhaFiltrada.add(2,linha.findElement(By.xpath("td[3]")));
						linhaFiltrada.add(3,linha.findElement(By.xpath("td[4]")));
						linhaFiltrada.add(4,linha.findElement(By.xpath("td[5]")));
					}
				}
			}
			
			return linhaFiltrada;
		}

		public DetalharLeilaoPage acaoExibir(List<WebElement> linha, WebDriver driver) {
			
			linha.get(5).findElement(By.xpath("a")).click();
			return new DetalharLeilaoPage(this.driver);
		}
	
	

}
