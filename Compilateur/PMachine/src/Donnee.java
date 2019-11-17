public class Donnee {
    public int adresse;
    public Type type;
    public boolean estGlobal;

    public Donnee(int adresse, Type type, boolean estGlobal){
        this.adresse = adresse;
        this.type = type;
        this.estGlobal = estGlobal;
    }

    public int getAd(){
        return this.adresse;
    }

    public Type getType(){
        return this.type;
    }

    public boolean getEstGloabl() { return  this.estGlobal; }
}
