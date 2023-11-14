package cw;

public class Ticket {
    int row;
    int seat;
    int price;
    Person person ;
    Ticket(int row,int seat,int price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
        
    }
    void print(){
        System.out.println("First name: "+person.name+"\n"+"Surname: "+person.surname+"\n"+"Email: "+person.email);
        System.out.println("Row: "+row+"\n"+"Seat: "+seat+"\n"+"Price: "+price);
    }
    
}
