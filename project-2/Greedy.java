import java.io.*;


public class Greedy {
    public static void readFile(String fileName) {

        BufferedReader reader = null;
        String line;
        boolean wrong = false;
        try {
            reader = new BufferedReader((new FileReader(new File(fileName))));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        try {
            ListInterface<Integer> files= new List<>();
            line = reader.readLine();
            while (line != null) {
                if (Integer.parseInt(line) > 1000000) { //if the given capacity of the folder is bigger than 1000000
                    System.out.println("Wrong value");
                    wrong = true;
                    break;
                }
                files.insert(Integer.parseInt(line));
                line = reader.readLine();
            }
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
            if (!wrong)
                storeFolders(files);

        } catch (IOException e) {
            System.out.println("Error reading line");
        }

    }

    public static void storeFolders(ListInterface<Integer> files){ //

        int size = files.size();

        ListInterface<Integer> sortedList;
        sortedList = files.copyList();
        sortedList.setHead(Sort.mergesort(sortedList.getHead())); //sorting the list

        MaxPQInterface pqUnsorted = new MaxPQ(files.size()+1); //the priority queue that stores disks
        //the first element of the pq is the disk with the biggest remaining space
        int numOfDisksUn= 1; //stores the total number of disks, also it is the id of every disk //πληθος δισκου
        pqUnsorted.add(new Disk(numOfDisksUn));//προσθετω στην ουρα τον δισκο
        int totalSumUn = 0; //total size of all folders //αθροισμα χωρητικοτητας φακελων πχ μβ




        MaxPQInterface pqSorted = new MaxPQ(sortedList.size()+1);//the priority queue that stores disks
        int numOfDisksS = 1; //stores the total number of disks, also it is the id of every disk
        pqSorted.add(new Disk(numOfDisksS));
        int totalSumS = 0;
        try {
            //while loop for the unsorted list
                if (files.peek() <= pqUnsorted.peek().getFreeSpace()) { //if size of the current folder fits in the disk
                    pqUnsorted.peek().folders.insert(files.peek()); //add the folder in the disk
				   totalSumUn += files.peek(); //αυξανω το μεγεθος των συνολικων φακελων
                    pqUnsorted.peek().setStorage(pqUnsorted.peek().getFreeSpace()-files.peek()); //decrease the free space of the disk //αφαιρω τη χωρητικοτητα του δισκου


                   //μπορει να εχει αλλαξει η χωρητικοτητα του δισκου(να εχει μικρινει) με αποτελεσμα να πρεπει να αλλαξει η προτεραιοτητα (να μην ειναι στην πρωτη θεση)
				   Disk removed = pqUnsorted.getMax();//the free space of the disk has change,so the position in the priority queue might also change
                   pqUnsorted.add(removed);


                    files.remove();//remove the folder from the list //αφαιρω το φακελο απο τη λιστα

                } else { //the size of the current folder does not fit in the disk //αν δε χωραει το αρχειο στο δισκο δημιουργω καινουριο δισκο, τον βαζω στην ουρα προτεραιοτητας πρωτο(παντα) και ακολουθει η ιδια διαδικασια
                    numOfDisksUn++;
                    pqUnsorted.add(new Disk(numOfDisksUn)); //create a new disk

                    pqUnsorted.peek().folders.insert(files.peek()); //add the folder in the disk
                    totalSumUn += files.peek();
                    pqUnsorted.peek().setStorage(pqUnsorted.peek().getFreeSpace()-files.peek()); //decrease the free space of the disk

                    Disk removed = pqUnsorted.getMax();//the free space of the disk has change,so the position in the priority queue might also change
                    pqUnsorted.add(removed);

                    files.remove(); //remove the folder from the list

                }
            }

            //while loop for the sorted list //η ιδια λιστα με πριν αλλα ταξινομημενη και κανω την ιδια διαδικασια
            while (!sortedList.isEmpty()) {
                if (sortedList.peek() <= pqSorted.peek().getFreeSpace()) {
                    pqSorted.peek().folders.insert(sortedList.peek());
                    totalSumS += sortedList.peek();
                    pqSorted.peek().setStorage(pqSorted.peek().getFreeSpace()-sortedList.peek());


                    Disk removed = pqSorted.getMax();
                    pqSorted.add(removed);

                    sortedList.remove();

                } else {
                    numOfDisksS++;
                    pqSorted.add(new Disk(numOfDisksS));

                    pqSorted.peek().folders.insert(sortedList.peek());
                    totalSumS += sortedList.peek();
                    pqSorted.peek().setStorage(pqSorted.peek().getFreeSpace()-sortedList.peek());

                    Disk removed = pqSorted.getMax();
                    pqSorted.add(removed);

                    sortedList.remove();

                }
            }



            System.out.println("Greedy Algorithm: ");
            System.out.println();

            System.out.println("Total sum is: " + totalSumUn);
            System.out.println("Total number of disks is: " + numOfDisksUn);

            if (size < 100) {
                for (int i = 0; i < numOfDisksUn; i++) {
                    System.out.print("id : " + pqUnsorted.peek().getId() + " free space: " + pqUnsorted.peek().getFreeSpace() + " mb: ");
                    pqUnsorted.peek().folders.printList();
                    System.out.println();
                    pqUnsorted.getMax();
                }
            }
            System.out.println();
            System.out.println("----------------------------------");
            System.out.println();
            System.out.println("Greedy-decreasing Algorithm: ");
            System.out.println();

            System.out.println("Total sum is: " + totalSumS);
            System.out.println("Total number of disks is: " + numOfDisksS);

            System.out.println();
            compare(numOfDisksUn, numOfDisksS);



        } catch (EmptyListException e) {
            System.out.println("The list is empty");
        }
    }


    public static void compare(int totalDisksUn, int totalDisksS) {
        if (totalDisksS == totalDisksUn)
            System.out.println("They need the same number of disks");
        else if (totalDisksS > totalDisksUn)
            System.out.println("The greedy algorithm needs less disks");
        else
            System.out.println("The greedy-decreasing algorithm needs less disks");
    }

    public static void main(String[] args) {
        writeFiles.WF(); //paragei 30 arxeia
        readFile(args[0]);

    }
}
