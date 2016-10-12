package kasiskimethod;

import java.util.ArrayList;
/**
 *
 * @author Jose Manuel Martínez de la Insua 
 */
public class Kasiski {
    
    private String str;
    
    public Kasiski(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
    
    // Encriptamos con clave
    public String encrypt(String key){
        int period = key.length();
        StringBuilder result = new StringBuilder();
        int lon = this.str.length();
        int index;
        for(int i = 0; i<lon;i++){
            index = (StrTools.ALPHABET.indexOf(this.str.charAt(i)) + StrTools.ALPHABET.indexOf(key.charAt(i%period)));
            while(index>25)index-=26;
            result.append((char) (StrTools.ALPHABET.charAt(index)));
        }
        return result.toString();
        
    }
    
    // Desencriptamos con clave
    public String decrypt(String key){
        int period = key.length();
        StringBuilder result = new StringBuilder();
        int lon = this.str.length();
        int index;
        for(int i = 0; i<lon;i++){
            index = (StrTools.ALPHABET.indexOf(this.str.charAt(i)) - StrTools.ALPHABET.indexOf(key.charAt(i%period)));
            while(index<0)index+=26;
            result.append((char) (StrTools.ALPHABET.charAt(index)));
        }
        return result.toString();
    }
    
    // Generador de claves.
    
    public ArrayList<String> key_gen(int period,int precision){
        
        // Objeto StrTools para operar con cadenas.
        StrTools strtools = new StrTools();
        
        // Array con los bloques separados por periodo.
        ArrayList<String> blocks = strtools.separate_by_period(this.str, period);
        
        // Array de Arrays de caracteres. En cada Array de caracteres
        // tenemos ordenados los caracteres de cada bloque (que separamos anteriormente
        // por periodo) por frecuencias, de mayor a menor frecuencia.
        ArrayList<ArrayList<Character>> f_list = new ArrayList<>();
        
        ArrayList<LetraValor> lv;
        ArrayList<Character> ac;
        
        /*for(String s : blocks){
            lv = strtools.frequency_list(s);
            ac = new ArrayList<>();
            for(int i=0 ;i<25;i++)
                ac.add(lv.get(i).getLetter());
            f_list.add(ac);
        }*/
        for(String s : blocks){
            lv = strtools.frequency_list(s);
            ac = new ArrayList<>();
            for(int i=0 ;i<25;i++)
                ac.add(lv.get(i).getLetter());
            f_list.add(ac);
        }
        
        // Pasamos el recolector
        strtools.garbageCollector();

        // cache
        String tmp;
        
        // Ahora basta con convertir un array de arrays de caracteres a
        // Array de String donde cada uno de los String es una clave.
        // Las claves se odenan en el vector de mayor a menor posibilidad de ser
        // la clave correcta.
        
        // El vector magia se encarga de mover el contador para crear todas las
        // combinaciones posibles. El vector tendrá tantos contadores como caracteres
        // tendrá la clave.
        ArrayList<Integer> magia = new ArrayList<>();
        
        for(int i = 0;i<period;i++) 
            magia.add(0);
        // Array con las claves ya construídas.
        ArrayList<String> result = new ArrayList<>();
        
        // Por combinatoria sacamos el numero total de claves.
        int num_keys = (int) Math.pow(precision*1.0f,period);
        
        for(int i = 0; i < num_keys; i++ ){
            tmp = "";
            // Creamos y agregamos una clave.
            for(int j = 0;j<period;j++)
                tmp+= f_list.get(j).get(magia.get(j));
            result.add(tmp);
            
            // Incrementamos el contador del vector con los contadores.
            magia.set(period-1,magia.get(period-1)+1);
            for(int k = 0; k < magia.size()-1;k++){
                if(magia.get(k+1)==precision){
                    magia.set(k+1,0);
                    magia.set(k,magia.get(k)+1);
                }
            }
        }
        
        return result;
    }
    
    
}
