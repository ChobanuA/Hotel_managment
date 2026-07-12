package model;

public class Payment {

    private int idPayment;
    private int idReservation;
    private double suma;
    private String dataPlata;
    private String metoda;


    public Payment() {
    }


    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }


    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }


    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }


    public String getDataPlata() {
        return dataPlata;
    }

    public void setDataPlata(String dataPlata) {
        this.dataPlata = dataPlata;
    }


    public String getMetoda() {
        return metoda;
    }

    public void setMetoda(String metoda) {
        this.metoda = metoda;
    }

}
