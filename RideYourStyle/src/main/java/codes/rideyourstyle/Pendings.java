package codes.rideyourstyle;

import javafx.collections.ObservableList;

import java.sql.Blob;

public class Pendings {
    private int id;
    private int user_id;
    private String customerName;
    private StringBuilder requests;
    private String status;
    private String ManagedBy;

    Blob jasper_report;

    public Pendings(int user_id, StringBuilder requests, String status, String managedBy) {
        this.user_id = user_id;
        LoginDatabaseConnection databaseConnection = new LoginDatabaseConnection();
        ObservableList<Info> users = databaseConnection.retrieveDatabase("user");
        for(Info user : users){
            if(user.id==user_id){
                this.customerName=user.Name;
            }
        }
        this.requests = requests;
        this.status = status;
        this.ManagedBy = managedBy;
    }
    public Pendings(int id, int user_id, StringBuilder requests, String status, String managedBy) {
        this.id = id;
        this.user_id = user_id;
        LoginDatabaseConnection databaseConnection = new LoginDatabaseConnection();
        ObservableList<Info> users = databaseConnection.retrieveDatabase("user");
        for(Info user : users){
            if(user.id==user_id){
                this.customerName=user.Name;
            }
        }
        this.requests = requests;
        this.status = status;
        this.ManagedBy = managedBy;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public StringBuilder getRequests() {
        return requests;
    }

    public void setRequests(StringBuilder requests) {
        this.requests = requests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManagedBy() {
        return ManagedBy;
    }

    public void setManagedBy(String managedBy) {
        ManagedBy = managedBy;
    }

    public Blob getJasper_report() {
        return jasper_report;
    }

    public void setJasper_report(Blob jasper_report) {
        this.jasper_report = jasper_report;
    }
}
