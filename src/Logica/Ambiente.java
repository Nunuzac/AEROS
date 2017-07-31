package Logica;

import java.awt.Point;

public class Ambiente {
    private Point coords;
    private int cantidadindividuos;
    private int mcmcuadrantes, mcmpatrulleros;
    private int longCAI, longcuadrante, longpatrullero;
    private int tamgenotipo;
    private Poblacion actual;
    private int genactual;
    private DBHandler handler;
        
    public Ambiente(Point coords, int cantidadindividuos, DBHandler handler){
        this.handler = handler;
        this.coords = coords;
        this.genactual = 0;
        this.actual = new Poblacion();
        this.cantidadindividuos = cantidadindividuos;
        this.mcmcuadrantes = 128;
        this.mcmpatrulleros = 256;
        this.longCAI = (int)Math.ceil(Math.log(handler.getTotalCAIS()) / Math.log(2));
        this.longcuadrante = (int)Math.ceil(Math.log(mcmcuadrantes) / Math.log(2));
        this.longpatrullero = (int)Math.ceil(Math.log(mcmpatrulleros) / Math.log(2));
        this.tamgenotipo = this.longCAI + this.longcuadrante + this.longpatrullero;
    }
    
    public void generarPoblacionInicial(){
        int corte1 = this.longCAI, corte2 = this.longCAI + this.longpatrullero;
        Integer dado ;
        for (int i =0; i < this.cantidadindividuos; i++){
            String agregar = "";
            for(int j = 0; j < this.tamgenotipo; j++){
                dado = ((int)(Math.random() * 10)) % 2;
                agregar = agregar.concat(dado.toString());
            }
            this.actual.add(new Individuo(agregar, corte1, corte2));
        }
    }
    
    public void pasarGeneracion(){
        double distancia;
        int disponibilidad, facilidad;
        for(int i = 0; i < this.actual.size(); i++){
            actual.get(i).obtenerFenotipo();
            distancia = this.coords.distance(handler.getPosicion(actual.get(i).getIdCAI(), actual.get(i).getIdCuadrante(), actual.get(i).getIdPatrullero()));
            disponibilidad = handler.getDisponibilidad(actual.get(i).getIdCAI(), actual.get(i).getIdCuadrante(), actual.get(i).getIdPatrullero());
            facilidad = handler.getFacilidad(actual.get(i).getIdCAI(), actual.get(i).getIdCuadrante(), actual.get(i).getIdPatrullero());
            actual.get(i).calcularFitness(distancia, disponibilidad, facilidad);
        }
        actual.sort();
        
        int mitad = this.cantidadindividuos / 2;
        int inferior = 0;
        int superior = mitad;
        Individuo hijos[] = new Individuo[2];
        Poblacion todos = new Poblacion();
        for(int i = 0; i < this.actual.size(); i++){
            todos.add(actual.get(i));
        }
        for(int i = 0; i < mitad; i++){
            if(inferior == superior)
                superior ++;
            hijos = actual.get(inferior).recombinar(actual.get(superior));
        
            hijos[0].mutar((float)0.4, 2);
            hijos[1].mutar((float)0.4, 2);
            
            todos.add(hijos[0]);
            todos.add(hijos[1]);
            inferior ++;
            superior --;
        }
        for(int i = 0; i < todos.size(); i++){
            todos.get(i).obtenerFenotipo();
            distancia = this.coords.distance(handler.getPosicion(todos.get(i).getIdCAI(), todos.get(i).getIdCuadrante(), todos.get(i).getIdPatrullero()));
            disponibilidad = handler.getDisponibilidad(todos.get(i).getIdCAI(), todos.get(i).getIdCuadrante(), todos.get(i).getIdPatrullero());
            facilidad = handler.getFacilidad(todos.get(i).getIdCAI(), todos.get(i).getIdCuadrante(), todos.get(i).getIdPatrullero());
            todos.get(i).calcularFitness(distancia, disponibilidad, facilidad);
        }
        todos.sort();
        
        actual.clear();
        for(int i = 0; i < this.cantidadindividuos; i++){
            actual.add(todos.get(i));
        }
        for(int i = 0; i < this.actual.size(); i++){
            actual.get(i).obtenerFenotipo();
            distancia = this.coords.distance(handler.getPosicion(actual.get(i).getIdCAI(), actual.get(i).getIdCuadrante(), actual.get(i).getIdPatrullero()));
            disponibilidad = handler.getDisponibilidad(actual.get(i).getIdCAI(), actual.get(i).getIdCuadrante(), actual.get(i).getIdPatrullero());
            facilidad = handler.getFacilidad(actual.get(i).getIdCAI(), actual.get(i).getIdCuadrante(), actual.get(i).getIdPatrullero());
            actual.get(i).calcularFitness(distancia, disponibilidad, facilidad);
        }
        actual.sort();
        this.genactual ++;
    }

    public Poblacion getActual() {
        return actual;
    }

    public int getCantidadindividuos() {
        return cantidadindividuos;
    }

    public int getGenActual() {
        return genactual;
    }
}
