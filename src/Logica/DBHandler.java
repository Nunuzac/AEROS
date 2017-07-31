package Logica;

import java.awt.Point;

public class DBHandler {
    
    private int cuadrantesxCAI = 128;
    private int patrullerosxcuadrante = 256;
    private int totalCAIS = 64;
    
    public DBHandler(){  
    }

    public int getCuadrantesPorCAI() {
        return cuadrantesxCAI;
    }

    public int getPatrullerosPorCuadrante() {
        return patrullerosxcuadrante;
    }

    public int getTotalCAIS() {
        return totalCAIS;
    }
    
    public Point getPosicion(int idCAI, int idcuadrante, int idpatrullero){
        return new Point((idCAI+idpatrullero) * idcuadrante, (idCAI+idcuadrante) * idpatrullero);
    }
    
    public int getDisponibilidad(int idCAI, int idcuadrante, int idpatrullero){
        return (idCAI + idcuadrante + idpatrullero) % 2;
    }
    
    public int getFacilidad(int idCAI, int idcuadrante, int idpatrullero){
        return (idCAI + idcuadrante + idpatrullero) % 5;
    }
}
