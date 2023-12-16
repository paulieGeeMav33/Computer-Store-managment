package store;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
//import java.util.Iterator;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.util.HashSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store {
    public Store(String name) {
        this.name = name;
    }
     public Store(BufferedReader br) throws IOException {
        this.name = br.readLine();
        int numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            customers.add(new Customer(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            options.add(new Option(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            computers.add(new Computer(br));

        numOptions = Integer.parseInt(br.readLine());
        while(numOptions-- > 0)
            orders.add(new Order(br));
    }
    public void save(BufferedWriter bw) throws IOException {
        /*bw.write(name + '\n');
        
        bw.write("" + customers.size() + '\n');
        for(Customer customer : customers)
            customer.save(bw);

        bw.write("" + options.size() + '\n');
        for(Option option : options)
            option.save(bw);

        bw.write("" + computers.size() + '\n');
        for(Computer computer : computers)
            computer.save(bw);

        bw.write("" + orders.size() + '\n');
        for(Order order : orders)
            order.save(bw); */
        bw.write(name + '\n');
        save(bw,customers);
        save(bw,options);
        save(bw,computers);
        save(bw,orders);

    }

    private <T extends Savable> void save(BufferedWriter bw, Set<T> set) throws IOException{
        bw.write("" + set.size() + '\n');
        for(var v : set){
            v.save(bw);
        }
    }

   public String name() {
        return this.name;
    }
    
    // ///////////////////////////////////////////////////////////
    // Customers
    
    public void add(Customer customer) {
        if(!customers.contains(customer)) customers.add(customer);
    }
    public Object[] customers() {
        return this.customers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Options
    
    public void add(Option option) {
        java.util.Iterator <Option> it = options.iterator();
        while(it.hasNext()){
            Option op = it.next();
            if(op.getDepricated()){
                it.remove();
            }
        }
        if(!options.contains(option)) options.add(option);
    }
    public Object[] options() {
        return this.options.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Computers
    
    public void add(Computer computer) {
        if(!computers.contains(computer)) computers.add(computer);
    }
    public Object[] computers() {
        java.util.Iterator <Computer> it = computers.iterator();
        while(it.hasNext()){
            Computer cp = it.next();
            if(cp.getDepricated()){
                it.remove();
            }
        }
        return this.computers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Orders
    
    public void add(Order order) {
        if(!orders.contains(order)) orders.add(order);
    }
    public Object[] orders() {
        return this.orders.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Fields
    
    private String name;
    private TreeSet<Customer> customers = new TreeSet<>();
    private HashSet<Option> options = new HashSet<>();
    private HashSet<Computer> computers = new HashSet<>();
    private HashSet<Order> orders = new HashSet<>();
}
