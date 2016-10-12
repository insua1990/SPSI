package kasiskimethod;

/**
 *
 * @author Jose Manuel Martínez de la Insua 
 */

// Clase que representa una Estructura de Datos necesaria para trabajar con
// letras y un valor asignado a cada letra.

public class LetraValor {
    private char letter;
    private float value;

    public LetraValor() {}

    public LetraValor(char letter, float value) {
        this.letter = letter;
        this.value = value;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    
}
