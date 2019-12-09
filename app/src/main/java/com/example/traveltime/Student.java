package com.example.traveltime;

public class Student {

    private  String NombreViajes;



    public Student(String nombreViajes){

        this.NombreViajes = nombreViajes;

    }

    public String getNombreViajes() {
        return NombreViajes;
    }

    public void setNombreViajes(String nombreViajes) {
        NombreViajes = nombreViajes;
    }


    public Student(){
    }

    public String toString(){
        return this.NombreViajes;
    }
}
