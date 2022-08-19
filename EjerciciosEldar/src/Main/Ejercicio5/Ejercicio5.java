package Main.Ejercicio5;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Ejercicio5 {
    public static void main(String[] args) {

        Streams array = new Streams();
        // me imprime la consigna del ejercicio... los une con un espacio, convierte todo a minuscula, y envia el resultado al stdout.
        array.print();
        //a traves de map convierte todo a LowerCase y lo imprime
        array.transform();
        //ordena todo con sorted() y lo imprime con un foreach
        array.order();
        //Una de las restricciones es que el tamaño no supere los 10
        array.count();
    }
}

class Streams {
    private List<String> myArray;

    public Streams() {
        myArray = new ArrayList<>();
        myArray.add("CAMERA");
        myArray.add("Art");
        myArray.add("DeLiVeRy");
        myArray.add("ReFlEcTiOnS");
        myArray.add("Sleeping");
        myArray.add("COFFEE");
        myArray.add("Mouseover");
        myArray.add("Ponytail");
        myArray.add("NOCTURNAL");
        myArray.add("Goat");

    }

    public void transform() {
        myArray.stream().map(String::toLowerCase).forEach(System.out::println);

    }

    public void count() {
        System.out.println(myArray.stream().count());

    }

    public void order() {
        myArray.stream().sorted().forEach(System.out::println);
    }

    public void print() {
        for (int i = 0; i < myArray.size(); i++) {
            System.out.print(myArray.get(i).toLowerCase() + " ");
        }
    }

}



