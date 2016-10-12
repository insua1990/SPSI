package kasiskimethod;

import java.util.ArrayList;

/**
 *
 * @author Jose Manuel Martínez de la Insua 
 */

public class StrTools {
    
    // IC del castellano
    public final float IC = 0.0775f; 
    // Alfabeto inglés
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    

    public StrTools() {}
    
    // Retorna la frecuencia de un caracter c en una cadena str.
    public static int frequency(String str,char c){
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == c)
                count ++;
        }
        return count;
    }
    
    // Retorna una lista de pares LetraValor los cuales indican la frecuencia y
    // la letra. Retorna ordenado de mayor a menos frecuencia de aparicion.
    
    public ArrayList<LetraValor> frequency_list(String str){
        
        // Llenamos el vector con cada letra y su frecuencia.
        ArrayList<LetraValor> lv =  new ArrayList<>();
        for(int i = 0; i < ALPHABET.length(); i++)
            lv.add(new LetraValor(ALPHABET.charAt(i), frequency(str,ALPHABET.charAt(i))));
        
        // Lo ordenamos con Bubble Sort.
        boolean swapped = true;
        int j = 0;
        LetraValor tmp;
        while (swapped) {
              swapped = false;
              j++;
              for (int i = 0; i < lv.size() - j; i++) {                                       
                    if (lv.get(i).getValue() < lv.get(i+1).getValue()) {  
                          tmp = lv.get(i);
                          lv.set(i,lv.get(i+1)) ;
                          lv.set(i+1,tmp);
                          swapped = true;
                    }
              }                
        }
        
        // Pasamos el recolector.
        garbageCollector();
        return lv;
    }
          
    
    // Calcula las repeticiones periodicas de una subcadena str.
    // El parametro n sirve para identificar la longitud del N-grama a buscar.
    
    public boolean ngrama(String str,int n){
        boolean find = false;
        String substr1,substr2;
        for(int i = 0; i < str.length() - n + 1; i++ ){
            substr1 = str.substring(i,i+n);
            for(int j = i+n; j < str.length() - n + 1 ; j++ ){
                substr2 = str.substring(j,j+n);
                if(substr1.compareTo(substr2)==0){
                   System.out.println(substr1+" -> Distancia :  "+(j-i));
                   find = true;
                }
            }
        }
        
        // Pasamos el recolector.
        garbageCollector();
        return find;
    }
    
    // Separa una cadena en "period" subcadenas, dichas subcanedas retornan
    // en forma de Arraylist. El reparto es equitativo y sigue el mismo patron.
    
    public ArrayList<String> separate_by_period(String str,int period){
        ArrayList<String> blocks = new ArrayList<>();
        for (int i = 0; i<period; i++)
            blocks.add(""); 
        int index;
        for (int i = 0; i<str.length(); i++){
            index = i%period;
            blocks.set(index,blocks.get(index)+str.charAt(i));
        }
        return blocks;
    }
    
    
    // Calculamos el IC a cada uno de los bloques separados y quedan almacenados
    // en un array de tipo double.
    
    public int period_by_ic(String str){
        ArrayList<String> blocks;
        ArrayList<Double> ics = new ArrayList<>();
        int lon = str.length();
        double n,ic,fi;
        int count;
        for(int T = 2; T < lon; T++){
            blocks = this.separate_by_period(str, T);
            for (String s : blocks) {
                ic = 0.0;
                n = s.length()*1.0;
                for(int i = 0; i < 26; i++){
                    fi = (double) StrTools.frequency(s,ALPHABET.charAt(i));
                    ic += ((fi)*(fi) - 1.0)/(n*(n - 1.0));
                }
                ics.add(ic);
                count = 0;
                for (Double d : ics) 
                    if(Math.abs(this.IC - d) < 0.015) 
                        count ++;

                if(count > T - 1) return T;
            }
        }
        // Pasamos el recolector.
        garbageCollector();
        return -1;
    }
    
    
    // Pasamos el recolector para eliminar todas las instancias con referencia 
    // nula o no usada.
    
    public void garbageCollector(){
        Runtime.getRuntime().gc();
    }
    
}
