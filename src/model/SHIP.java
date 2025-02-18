package model;

public enum SHIP { //ship enum that stores each type of ship
	
	BLUE("view/resources/shipchooser/ships/ship_1.png", "view/resources/shipchooser/playerLife_blue.png"), // url of space ship 1 and player life blue
	GREEN("view/resources/shipchooser/ships/ship_2.png", "view/resources/shipchooser/playerLife_green.png"),// url of space ship 2 and player life green
	ORANGE("view/resources/shipchooser/ships/ship_3.png", "view/resources/shipchooser/playerLife_orange.png"),// url of space ship 3 and player life orange
	RED("view/resources/shipchooser/ships/ship_4.png", "view/resources/shipchooser/playerLife_red.png");// url of space ship 4 and player life red
	
	private String urlShip; 
	private String urlLife;
	
	private SHIP(String urlShip, String urlLife) {
		this.urlShip = urlShip; // 
		this.urlLife = urlLife;
	}
	
	public String getUrl() {
		return urlShip;
	}
	
	public String getUrlLife() { 
		return urlLife;
	}
}
