package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		try {
			Etal etal = new Etal();
			Gaulois gaulois = new Gaulois("Gauloux", 5);
			etal.acheterProduit(-2, gaulois);
			etal.acheterProduit(2, null);
			etal.acheterProduit(2, gaulois);
			System.out.println("Fin du test\n");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}
}
