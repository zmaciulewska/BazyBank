package models;
//Data Transfer Model for Account table\


public class Account {

    private Integer idClient,id;
    private float balance;
    private String notes;


    public Account() {}
    public Account(Integer id, Integer idClient, String notes, float balance) {
        this.id = id;
        this.idClient = idClient;
        this.notes = notes;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", notes='" + notes + '\'' +
                ", balance=" + balance +
                '}';
    }
}
