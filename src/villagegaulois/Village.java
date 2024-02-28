package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	private static class Marche {
		private Etal[] etals;

		private Marche(int nombreEtals) {
			etals = new Etal[nombreEtals];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtal = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbEtal++;
				}
			}
			Etal[] resEtal = new Etal[nbEtal];
			for (int i = 0, j = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					resEtal[j] = etals[i];
					j++;
				}
			}
			return resEtal;
		}

		private Etal trouverVendeur(Gaulois vendeur) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur().equals(vendeur)) {
					return etals[i];
				}
			}
			return null;
		}

		private String afficherMarche() {
			StringBuilder message = new StringBuilder();
			int nbEtalsVide = 0;
			for (int i = 0; i < etals.length; i++) {
				message.append(etals[i].afficherEtal());
				if (!etals[i].isEtalOccupe()) {
					nbEtalsVide++;
				}
			}
			if (nbEtalsVide != 0) {
				message.append("Il reste " + nbEtalsVide + " �tals non utilis�s dans le march�.\n");
			}
			return message.toString();
		}
	}

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
			throw new VillageSansChefException("Il n'y a pas de chef dans le village");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder message = new StringBuilder();
		message.append(vendeur.getNom() + " cherche un endroit ou vendre " + nbProduit + " " + produit + ".\n");
		int numEtal = marche.trouverEtalLibre();
		if (numEtal == -1) {
			message.append(
					"Toutes les �tales sont occup�es, le vendeur " + vendeur.getNom() + " devra revenir demain.\n");
		} else {
			marche.utiliserEtal(numEtal, vendeur, produit, nbProduit);
			message.append("Le vendeur " + vendeur.getNom() + " vend " + produit + " � l'�tal n� " + numEtal + ".\n");
		}
		return message.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder message = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit.length == 0) {
			message.append("Il n'y a pas de vendeur qui propose " + produit + " au march�.\n");
		} else if (etalsProduit.length == 1) {
			message.append("Seul le vendeur " + etalsProduit[0].getVendeur().getNom() + " propose " + produit
					+ " au march�.\n");
		} else {
			message.append("Les vendeurs qui proposent " + produit + " sont :\n");
			for (int i = 0; i < etalsProduit.length; i++) {
				message.append("- " + etalsProduit[i].getVendeur().getNom() + "\n");
			}
		}
		return message.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		Etal etalVendeur = marche.trouverVendeur(vendeur);
		return etalVendeur.libererEtal();
	}

	public String afficherMarche() {
		return marche.afficherMarche();
	}

}
