package etu2061.framework.bdd;

import etu2061.framework.annotation.UrlAnnotation;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public class Employe {
    String nom;
    String prenom;
    int age;
    String email;
    String adresse;

    @UrlAnnotation(url = "getnom")
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Employe(){}

    public Employe(String nom, String prenom, int age, String email, String adresse){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setAge(age);
        this.setEmail(email);
        this.setAdresse(adresse);
    }
}
