package fr.eni.abono.bo;

public class Offer {
    private String libelleproduit;
    private float prix;

    public String getLibelleproduit() {
        return libelleproduit;
    }

    public void setLibelleproduit(String libelleproduit) {
        this.libelleproduit = libelleproduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "libelleproduit='" + libelleproduit + '\'' +
                ", prix=" + prix +
                '}';
    }
}
