package model;

public class Room {

    private int idRoom;
    private int numar;
    private String tip;
    private double pret;
    private String status;

    public Room() {
    }

    public Room(int idRoom, int numar, String tip, double pret, String status) {
        this.idRoom = idRoom;
        this.numar = numar;
        this.tip = tip;
        this.pret = pret;
        this.status = status;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}