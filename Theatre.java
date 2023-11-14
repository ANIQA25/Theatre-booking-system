package cw;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Theatre {

    static void buy_ticket(int[] row1, int[] row2, int[] row3, ArrayList<ArrayList<String>> ticketsList) {
        Scanner scanner = new Scanner(System.in);
        boolean rowexists = false;
        int rownum = 0;
        boolean seatexists = false;
        int seatnum = 0;
        //checks if row exists or row entered is out of bound
        while ((rowexists == false)) {
            System.out.println("Enter row number to check for availability: ");
            rownum = scanner.nextInt();
//            scanner.nextLine();
            if (rownum > 0 && rownum < 4) {
                rowexists = true;
            } else {
                System.out.println("Row doesn't exist");
                rowexists = false;
                continue;
            }

            while (seatexists == false) {
                System.out.println("Enter seat number to check for availability: ");
                seatnum = scanner.nextInt();
                scanner.nextLine();
                if ((rownum == 1 && seatnum > -1 && seatnum < 12) || (rownum == 2 && seatnum > 0 && seatnum < 16) || (rownum == 3 && seatnum > 0 && seatnum < 20)) {
                    if ((rownum == 1 && row1[seatnum-1] == 1) || (rownum == 2 && row2[seatnum-1] == 1) || (rownum == 3 && row3[seatnum-1] == 1)) {
                        System.out.println("Occupied");
                        seatexists = false;
                        rowexists = false;
                        break;
                    } else {
                        System.out.println("Your seat has been booked!");
                        if (rownum == 1) {
                            row1[seatnum-1] = 1;
                        } else if (rownum == 2) {
                            row2[seatnum-1] = 1;
                        } else {
                            row3[seatnum-1] = 1;
                            seatexists = true;
                            rowexists = true;
                        }
                        break;
                    }

                } else {
                    System.out.println("Seat doesn't exist");
                    seatexists = false;

                }
            }
        }


        System.out.println("Please enter your name:");
        String fname = scanner.nextLine();


        System.out.println("Please enter your last name: ");
        String lname = scanner.nextLine();

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Please enter the price you wish to pay: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        Person person = new Person(fname, lname, email);
        Ticket ticket = new Ticket(rownum, seatnum, price, person);


        ArrayList<String> nested = new ArrayList<String>();
        nested.add(Integer.toString(rownum));
        nested.add(Integer.toString(seatnum));
        nested.add(Integer.toString(price));
        nested.add(fname);
        nested.add(lname);
        nested.add(email);
        ticketsList.add(nested);
        System.out.println(ticketsList);

    }

    static void print_seating_area(int[] row1, int[] row2, int[] row3) {
        String srow1 = "";
        String srow2 = "";
        String srow3 = "";
        for (int x = 0; x < row1.length; x++) {
            if (row1[x] == 0) {
                srow1 = srow1 + 'O';
            } else srow2 = srow2 + 'X';
        }
        for (int y = 0; y < row2.length; y++) {
            if (row2[y] == 0) {
                srow2 = srow2 + 'O';
            } else srow2 = srow2 + 'X';
        }
        for (int z = 0; z < row3.length; z++) {
            if (row3[z] == 0) {
                srow3 = srow3 + 'O';
            } else srow3 = srow3 + 'X';
        }

        System.out.println("    " + "*".repeat(11) + "      \n\n    *  STAGE  *      \n\n" + "    " + "*".repeat(11) + "\n");
        System.out.println("    " + srow1.substring(0, 6) + " " + srow1.substring(6, 12) + "\n");
        System.out.println("  " + srow2.substring(0, 8) + " " + srow2.substring(8, 16) + "\n");
        System.out.println(srow3.substring(0, 10) + " " + srow3.substring(10, 20));

    }

    static void cancel_ticket(int[] row1, int[] row2, int[] row3, ArrayList<ArrayList<String>> ticketsList) {
        Scanner scanner = new Scanner(System.in);
        boolean rowexists = false;
        int rownum = 0;
        boolean seatexists = false;
        int seatnum = 0;
        while (rowexists == false) {
            System.out.println("Enter row number: ");
            rownum = scanner.nextInt();
            scanner.nextLine();
            if (rownum < 1 || rownum > 3) {
                System.out.println("Row doesn't exist!");
                rowexists = false;
                continue;

            } else {
                while (seatexists == false) {
                    System.out.println("Enter seat number to cancel: ");
                    seatnum = scanner.nextInt();
                    if ((rownum == 1 && seatnum > 0 && seatnum < 12) || (rownum == 2 && seatnum > 0 && seatnum < 16) || (rownum == 3 && seatnum > 0 && seatnum < 20)) {
                        if (rownum == 1) {
                            row1[seatnum-1] = 0;
                        } else if (rownum == 2) {
                            row2[seatnum-1] = 0;
                        } else if (rownum == 3) {
                            row3[seatnum-1] = 0;

                        }
                        System.out.println("Your seat has been cancelled! ");
                        for (int i = 0; i < ticketsList.size(); i++) {
                            if ((Integer.toString(rownum).equals(ticketsList.get(i).get(0))) && (Integer.toString(seatnum).equals(ticketsList.get(i).get(1)))) {
                                ticketsList.remove(i);
                            }

                        }

                        seatexists = true;
                        rowexists = true;

                    } else {
                        System.out.println("Seat doesn't exist");
                        rowexists = false;
                    }
                    break;
                }
            }
        }

    }

    static void show_available(int[] row1, int[] row2, int[] row3) {
        String string1 = "";
        for (int x = 0; x < (row1.length); x++) {
            if (row1[x] == 0) {
                if (x != 11) {
                    string1 = string1 + Integer.toString(x+1) + ", ";
                } else {
                    string1 = string1 + Integer.toString(x+1) + ".";
                }

            }
        }
        System.out.println("Seats available in row 1: " + string1);
        String string2 = "";
        for (int y = 0; y < (row2.length); y++) {
            if (row2[y] == 0) {
                if (y != 15) {
                    string2 = string2 + Integer.toString(y+1) + ", ";
                } else {
                    string2 = string2 + Integer.toString(y+1) + ".";
                }

            }
        }
        System.out.println("Seats available in row 2: " + string2);
        String string3 = "";
        for (int z = 0; z < (row3.length); z++) {
            if (row3[z] == 0) {
                if (z != 19) {
                    string3 = string3 + Integer.toString(z+1) + ", ";
                } else {
                    string3 = string3 + Integer.toString(z+1) + ".";
                }

            }
        }
        System.out.println("Seats available in row 3: " + string3);
    }

    static void save(int[] row1, int[] row2, int[] row3) {
        try {
            File myObj = new File("theatre.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("theatre.txt");
            myWriter.write(Arrays.toString(row1) + "\n");
            myWriter.write(Arrays.toString(row2) + "\n");
            myWriter.write(Arrays.toString(row3) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An arror occurres.");
            e.printStackTrace();
        }
    }

    static void load() {
        try {
            File myObj = new File("theatre.txt");
            Scanner myreader = new Scanner(myObj);
            while (myreader.hasNextLine()) {
                String Data = myreader.nextLine();
                System.out.println(Data);
            }
            myreader.close();
        } catch (FileNotFoundException e) {
            System.out.println("an error occurred.");
            e.printStackTrace();
        }
    }

    static void show_tickets_info(ArrayList<ArrayList<String>> ticketsList) {
        int Tot_price = 0;
        for (int i = 0; i < ticketsList.size(); i++) {
            System.out.println("Row no: " + ticketsList.get(i).get(0));
            System.out.println("Seat no: " + ticketsList.get(i).get(1));
            System.out.println("Price: " + ticketsList.get(i).get(2));
            System.out.println("First name: " + ticketsList.get(i).get(3));
            System.out.println("Last name: " + ticketsList.get(i).get(4));
            System.out.println("Email: " + ticketsList.get(i).get(5));

            Tot_price = Tot_price + Integer.parseInt(ticketsList.get(1).get(2));
        }
        System.out.println("Total price: " + Tot_price);
    }

    static void sort_tickets(ArrayList<ArrayList<String>> ticketsList) {
        ArrayList<String> temp = new ArrayList<String>();


        int end=(ticketsList.size()-2);
        boolean swap=true;
        while (swap){
            swap=false;

            for(int i=0; i<=end; i++) {
                if ((Integer.parseInt(ticketsList.get(i).get(2))) > (Integer.parseInt(ticketsList.get(i + 1).get(2)))) {
                    temp = (ticketsList.get(i));
                    ticketsList.set(i, ticketsList.get(i + 1));
                    ticketsList.set(i + 1, temp);


                }
            }
        }




        System.out.println(ticketsList);
    }





    public static void main(String[] args) {
        System.out.println("Welcome to the new Theatre");
        System.out.println("Seat numbering starts from 0");
        int row1[] = new int[12];
        int row2[] = new int[16];
        int row3[] = new int[20];
        ArrayList<ArrayList<String>> ticketsList = new ArrayList<ArrayList<String>>();
        System.out.println("-".repeat(40) + "\n" + "Please select an option:\n1) Buy a ticket \n2) Print seating area \n3) Cancel ticket \n4) List available seats \n5) Save to file \n6) Load form file \n7) Print ticket information and total price \n8) Sort tickets by price \n" + "    0) Quit\n" + "-".repeat(40));
        Scanner scanner = new Scanner(System.in);

        int option;
        do {
            System.out.println("Enter option:");
            option = scanner.nextInt();
            if (option == 1) {

                buy_ticket(row1, row2, row3, ticketsList);
            } else if (option == 2) {
                print_seating_area(row1, row2, row3);
            } else if (option == 3) {
                cancel_ticket(row1, row2, row3, ticketsList);
            } else if (option == 4) {
                show_available(row1, row2, row3);
            } else if (option == 5) {
                save(row1, row2, row3);
            } else if (option == 6) {
                load();
            } else if (option == 7) {
                show_tickets_info(ticketsList);
            } else if (option == 8){
                sort_tickets(ticketsList);
            }
        } while (option != 0);

        System.exit(0);
    }
}
