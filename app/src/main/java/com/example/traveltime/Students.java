package com.example.traveltime;

public class Students {
    private  String NombreViajes;



    public Students(String nombreViajes){

        this.NombreViajes = nombreViajes;

    }

    public String getNombreViajes() {
        return NombreViajes;
    }

    public void setNombreViajes(String nombreViajes) {
        NombreViajes = nombreViajes;
    }


 public Students(){
 }

 public String toString(){
        return this.NombreViajes;
 }


}
