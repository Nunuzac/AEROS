package Logica;

import java.util.ArrayList;
import java.util.Collections;

public class Poblacion extends ArrayList<Individuo>{
    
    private double desviacion;
    private double promedio;
        
    public Poblacion(){
       super();
    }

    public void sort(){
        Collections.sort(this);
    }

    public double getDesviacion() {
        return desviacion;
    }

    public void calcularPromedio(){
        this.promedio = 0;
        for(int i = 0; i<this.size(); i++){
            this.promedio += this.get(i).getFitness();
        }
        this.promedio /= this.size();
    }
    
    public void calcularDesviacion(){
        this.desviacion = 0;
        for(int i = 0; i<this.size(); i++){
            this.desviacion += Math.pow(this.get(i).getFitness() - this.promedio, 2);
        }
        this.desviacion /= this.size();
        this.desviacion = (double)Math.sqrt(this.desviacion);
    }
    
    public double getPromedio() {
        return promedio;
    }
}
