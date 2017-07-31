package Logica;


public class Individuo implements Comparable<Individuo>{
    private String genotipo;
    private int idCAI, idcuadrante, idpatrullero;    
    private double fitness;
    private int corte1, corte2;
    
    public Individuo(String genotipo, int corte1, int corte2){
        this.genotipo = genotipo;
        this.corte1 = corte1;
        this.corte2 = corte2;
    }
    
    public void calcularFitness(double distancia, int disponibilidad, int facilidad){
        this.fitness = Math.pow((double)(distancia * (1.1 - 0.1 * facilidad)), 
                       Math.pow(100, 1 - disponibilidad));
    }
    
    public void obtenerFenotipo(){
        this.idpatrullero = Integer.parseInt(genotipo.substring(corte1, corte2), 2);
        this.idcuadrante = Integer.parseInt(genotipo.substring(corte2), 2);
        this.idCAI = Integer.parseInt(genotipo.substring(0, corte1), 2);
    }
    
    public void mutar(float prob, int cantidad){
        for (int i = 0; i < cantidad ; i++){
            double dado1 = Math.random();
            String copia = "";
            if(dado1 <= prob){
                int dado2 = (int)(Math.random() * this.genotipo.length() - 1) + 1;
                copia = genotipo.substring(0, dado2);
                if(this.genotipo.charAt(dado2) == '0')
                    copia = copia.concat("1");
                else
                    copia = copia.concat("0");
                copia = copia.concat(genotipo.substring(dado2 + 1, this.genotipo.length()));
                this.genotipo = copia;
            }
            
            copia = "";
        }
    }
    
    public int getIdPatrullero() {
        return idpatrullero;
    }

    public int getIdCuadrante() {
        return idcuadrante;
    }

    public int getIdCAI() {
        return idCAI;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Individuo i) {
        int retorno = (int)(this.fitness - i.getFitness());
        return retorno;
    }
    
    public Individuo[] recombinar(Individuo otro){
        String genohijo1 = "", genohijo2 = "";
        Individuo hijo1, hijo2;
        int mitad = (int)Math.ceil(this.genotipo.length() / 2);
        genohijo1 = genohijo1.concat(this.genotipo.substring(0, mitad));
        genohijo1 = genohijo1.concat(otro.getGenotipo().substring(mitad, genotipo.length()));
        genohijo2 = genohijo2.concat(otro.getGenotipo().substring(0, mitad));
        genohijo2 = genohijo2.concat(this.genotipo.substring(mitad, genotipo.length()));
        hijo1 = new Individuo(genohijo1, this.corte1, this.corte2);
        hijo2 = new Individuo(genohijo2, this.corte1, this.corte2);
        Individuo hijos[] = new Individuo[2];
        hijos[0] = hijo1;
        hijos[1] = hijo2;
        return hijos;
    }

    public String getGenotipo() {
        return this.genotipo;
    }

    public void setCortes(int corte1, int corte2) {
        this.corte1 = corte1;
        this.corte2 = corte2;
    }
}
