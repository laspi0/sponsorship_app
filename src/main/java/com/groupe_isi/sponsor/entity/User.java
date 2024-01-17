package com.groupe_isi.sponsor.entity;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private int actived;
    private Integer profil;

    public User(int id, String nom, String prenom, String login, String password, int actived, Integer profil) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.actived = actived;
        this.profil = profil;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActived() {
        return actived;
    }

    public void setActived(int actived) {
        this.actived = actived;
    }

    public Integer getProfil() {
        return profil;
    }

    public void setProfil(Integer profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nom: " + nom +
                ", Prenom: " + prenom +
                ", Login: " + login +
                // Add other fields as needed
                ", Actived: " + actived +
                ", Profil: " + profil;
    }
}
