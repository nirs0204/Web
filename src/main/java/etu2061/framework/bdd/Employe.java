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

    @UrlAnnotation(url = "/huhu/get")
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

    public ArrayList<String> getMethodAnnotee(){
        ArrayList<String> tabmaethod = new ArrayList<String>();
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof UrlAnnotation) {
                    tabmaethod.add(method.getName());
                    System.out.println("Method " + method.getName() + " is annotated with " + annotation.annotationType().getSimpleName());
                }
            }
        }
        return tabmaethod;
    }
}
