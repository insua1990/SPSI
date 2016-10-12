/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasiskimethod;

/**
 *
 * @author Jose
 */
public class Cesar{
   
    public Cesar() {}
    
    // Encriptamos con clave
    public String encrypt(String str,char key){
        key = Character.toUpperCase(key);
        int sum = StrTools.ALPHABET.indexOf(key);
        StringBuilder result = new StringBuilder();
        int lon = str.length();
        int index;
        for(int i = 0; i < lon ; i++){
            index = (StrTools.ALPHABET.indexOf(str.charAt(i)) + sum);
            while(index>25)index-=26;
            result.append((char) (StrTools.ALPHABET.charAt(index)));
        }
        return result.toString();
    }
    
    // Desencriptamos con clave
    public String decrypt(String str,char key){
        key = Character.toUpperCase(key);
        int rest = StrTools.ALPHABET.indexOf(key);
        StringBuilder result = new StringBuilder();
        int lon = str.length();
        int index;
        for(int i = 0; i < lon; i++){
            index = (StrTools.ALPHABET.indexOf(str.charAt(i)) - rest);
            while(index<0)index+=26;
            result.append((char) (StrTools.ALPHABET.charAt(index)));
        }
        return result.toString();
    }
    
    
    
}
