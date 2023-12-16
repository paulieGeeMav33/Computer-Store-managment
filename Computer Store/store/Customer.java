package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class Customer implements Comparable<Customer>,Savable{
    public Customer(String name, String email) {
        int atIndex = email.indexOf('@', 0);
        int dotIndex = (atIndex >= 0) ? email.indexOf('@', 0) : -1;
        if(dotIndex < 0) // will be true if atIndex < 0, so don't check that
            throw new IllegalArgumentException("Invalid email address: " + email);
        this.name = name;
        this.email = email;
    }
    public Customer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();
    }
    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }
    
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Customer c = (Customer) o;
            return this.name.equals(c.name) && this.email.equals(c.email);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,email);
    }

    @Override
    public int compareTo(Customer o){
        int num = this.name.compareTo(o.name);
        if(num == 0){
            num = this.email.compareTo(o.email);
        }
        return num;
    }
    private String name;
    private String email;
}
