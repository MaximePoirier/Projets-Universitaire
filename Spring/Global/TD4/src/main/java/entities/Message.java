package entities;


import javax.persistence.*;

@Entity
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmsg;
    private String contenu;
    @ManyToOne
    private Utilisateur auteur;

    public int getIdmsg() {
        return idmsg;
    }

    public String getContenu() {
        return contenu;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public void setIdmsg(int id) {
        this.idmsg = id;
    }

    public void setContenu(String msg) {
        this.contenu = msg;
    }

    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }
}
