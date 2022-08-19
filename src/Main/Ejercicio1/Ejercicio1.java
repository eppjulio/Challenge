package Main.Ejercicio1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static java.lang.String.valueOf;

/**
 * El metodo pagoDetalle() de la clase PagoCredito informa si una tarjeta es valida para operar segun su fecha de expiracion,
 * tambien informa si una operacion es valida segun las reglas de monto permitido en el sistema (menor a 1000), al mismo tiempo,
 * devuelve toda la informacion de una tarjeta.
 * El metodo tasaOperacion() de la clase PagoCredito identifica si una tarjeta es distinta a otra, y a traves del mismo,
 * obtenemos la tasa de una operacion informando marca e importe.
 */


public class Ejercicio1 {
    public static void main(String[] args) throws ParseException {
        //operacion normal
        PagoCredito pa = new PagoCredito(999.99, "nara", "12/23", "4642154554225874", "Julio Patino");
        pa.pagoDetalle();
        pa.tasaOperacion();

        //operacion superando el monto maximo
        PagoCredito paTwo = new PagoCredito(1003, "amex", "15/26", "4143794554425274", "Melisa Norman");
        paTwo.pagoDetalle();
        paTwo.tasaOperacion();

        //operacion con una tarjeta de credito expirada
        PagoCredito paThree = new PagoCredito(700, "visa", "11/21", "4041234557645274", "Azul Catanzaro");
        paThree.pagoDetalle();
        paThree.tasaOperacion();

        //operacion con una tarjeta de credito de marca no registrada en el sistema
        PagoCredito paFour = new PagoCredito(585, "Cabal", "11/26", "4955422557645894", "Dani Echeverria");
        paFour.pagoDetalle();
        paFour.tasaOperacion();

    }
}

class Pago {
    double monto;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    Pago(double val) {
        this.monto = Math.round(val * 100) / 100.0;
    }

    public void pagoDetalle() throws ParseException {
        System.out.println("El monto de pago es " + this.monto);
    }

}

class PagoEfectivo extends Pago {

    PagoEfectivo(double val) {
        super(val);
    }

    public void pagoDetalle() {
        System.out.println("El monto de pago en efectivo es de " + this.monto + "$");
    }
}

class PagoCredito extends Pago {
    String CardBrand, ExpiryDate, CardNumber, CardHolderName;

    PagoCredito(double Value, String CardBrand, String ExpiryDate, String CardNumber, String CardHolderName) {
        super(Value);
        this.CardBrand = CardBrand.toUpperCase();
        this.CardNumber = CardNumber;
        this.ExpiryDate = ExpiryDate;
        this.CardHolderName = CardHolderName;

    }

    String[] marcasAceptadas = {"VISA", "AMEX", "NARA"};

    public void pagoDetalle() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        Date expiracion = simpleDateFormat.parse(this.ExpiryDate);
        boolean expiracionValida = expiracion.before(new Date());
        boolean montoValido = (this.monto > 0) && (this.monto < 1000) ;
        boolean marcaRegistrada = this.CardBrand.equals(marcasAceptadas[0]) || this.CardBrand.equals(marcasAceptadas[1]) || this.CardBrand.equals(marcasAceptadas[2]);

        if (expiracionValida) {
            System.out.println("La operacion no se pudo realizar:");
            System.out.println("Tarjeta de credito expirada, intente con una nueva tarjeta.");
        } else if (montoValido&&marcaRegistrada) {
            System.out.println("La operacion se ha realizado con exito.");
            System.out.println("Monto de pago realizado: " + this.monto + "$" + " a nombre de: " + this.CardHolderName + ", con la tarjeta de credito numero: " + this.CardNumber + " marca: " + this.CardBrand + " con fecha de expiracion de: " + this.ExpiryDate);
        } else if(!montoValido){
            System.out.println("La operacion no se pudo realizar, debido a que el monto maximo para las mismas es de 1000");
            System.out.println("Por favor intente nuevamente con un monto valido.");
        } else {
            System.out.println("La operacion no se pudo realizar:");
        }
    }

    public void tasaOperacion() {

        LocalDate fecha = LocalDate.now();
        int anio = fecha.getYear() % 100;
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        double visa = anio / mes;
        double nara = (dia * 0.5);
        double amex = (mes * 0.1);


        if (this.CardBrand.equals(marcasAceptadas[0])) {
            System.out.println("El total de su tasa por el servicio " + marcasAceptadas[0] + " seria " + visa + "$");
        } else if (this.CardBrand.equals(marcasAceptadas[1])) {
            System.out.println("El total de su tasa por el servicio " + marcasAceptadas[1] + " seria " + amex + "$");
        } else if (this.CardBrand.equals(marcasAceptadas[2])) {
            System.out.println("El total de su tasa por el servicio " + marcasAceptadas[2] + " seria " + nara + "$");
        } else {
            System.out.println(this.CardBrand + " no es una marca aceptada por el sistema actualmente");
            System.out.println("No podemos calcular la tasa de su operacion, intente con otra tarjeta (Visa, Nara, Amex)");
        }
    }

}


