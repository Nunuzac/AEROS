package Logica;

import java.awt.Point;

public class Main {
    
    public static void main(String[] args) {
        Ambiente a = new Ambiente(new Point(1, 1), 100, new DBHandler());
        a.generarPoblacionInicial();
        for(int i = 1; i<=10; i++){
            a.pasarGeneracion();
            a.getActual().calcularPromedio();
            a.getActual().calcularDesviacion();
       
                System.out.println("-------------------------");
                System.out.println("Generación: "+i);
                
                System.out.println("Min: "+a.getActual().get(0).getFitness());
                System.out.println("Max: "+a.getActual().get(a.getActual().size() - 1).getFitness());
                
                System.out.println("Promedio: "+a.getActual().getPromedio());
                System.out.println("Desviacion: "+a.getActual().getDesviacion());
                
                
            
        }
    }
}
