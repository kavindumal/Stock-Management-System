import java.util.Scanner;

public class StockManagementSystem{
    private static Scanner input = new Scanner(System.in);
    private static String[][] idName = new String[0][2];
    private static String[][] items = new String[0][6];
    private static String[] itemsOnCategories = new String[0];
    private static String username = "Danuja";
    private static String password = "1234";
    private static String yOrN = "null";
    private static final int boxWidth = 98;
    private static int sup = 0;
    private static int cat = 0;
    private static int it = 0;

    public static void loginPage(){
        String welcomeText = "login page";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+------------------------------------------------------------------------------------------------+");

        boolean flag = false;
        do {
            System.out.printf("%n %-10s ","Username:");
            String name = input.next();
            if (name.equals(username)){
                do {
                    System.out.printf("%n %-10s ","Password:");
                    String pw = input.next();
                    if (pw.equals(password)){
                        flag = true;
                        clearConsole();
                        homePage();
                    }else {
                        System.out.printf(" %-10s %n","Password is invalid! Please try again with valid password....");
                        continue ;
                    }
                }while (!flag);
            }else System.out.printf(" %-10s %n %n","Username is invalid! Please try again with valid username....");
        }while (!flag);
    }

    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) System.out.print("\033\143");
            else if (os.equals("Windows")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void homePage(){
        String welcomeText = "Welcome to IJSE Stock Management System";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        System.out.printf("%n %s %-50s %s %s %n %s %-50s %s %s %n %s %s %n %n","[1]","Change the Credentials","[2]","Supplier Manage","[3]","Stock Manage","[4]","Log Out","[5]","Exit the System");

        System.out.print("Enter an option to Continue > ");
        int option = input.nextInt();

        if(option == 1){
            clearConsole();
            changeCredentianals();
        } else if (option == 2) {
            clearConsole();
            supplierManage();
        } else if (option == 3) {
            clearConsole();
            stockManage();
        } else if (option == 4) {
            clearConsole();
            loginPage();
        } else System.exit(0);
    }

    public static void stockManage(){
        String welcomeText = "stock management";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        System.out.printf("%n %s %-50s %s %s %n %s %-50s %s %s %n %s %-50s %s %s %n %n","[1]","Manage Item Categories","[2]","Add Items","[3]","Get Items Supplier Wise ","[4]","View Items","[5]","Rank Items Per Unit Price","[6]","Home Page");

        System.out.print("Enter an option to Continue > ");
        int option = input.nextInt();

        if (option == 1) {
            clearConsole();
            manageItemCategories();
        } else if (option == 2) {
            clearConsole();
            addItems();
        } else if (option == 3) {
            clearConsole();
            searchItems();
        } else if (option == 4) {
            clearConsole();
            viewItems();
        } else if (option == 5) {
            clearConsole();
            rankedUnitPrice();
        } else if (option == 6) {
            clearConsole();
            homePage();
        }
    }

    public static void rankedUnitPrice(){
        String welcomeText = "ranked unit price";
        int padding = (boxWidth - welcomeText.length() - 2) / 2; 

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (items.length == 0){
            System.out.printf("%n %s","No items found. Do you want to go stock manager (y or n)");
            yOrN = input.next();
            if (yOrN.equals("y")){
                clearConsole();
                stockManage();
            }else return;
        }
        double[] ar = new double[items.length];

        double temp = Double.MIN_VALUE;
        for (int i = 0; i < items.length; i++) {
            ar[i] = Double.parseDouble(String.valueOf(items[i][4]));
        }
        for (int i = 0; i < ar.length - 1; i++) {
            for (int j = 0; j <ar.length -1; j++) {
                if (ar[j] > ar[j+1]) {
                    temp = ar[j];
                    ar[j] = ar[j+1];
                    ar[j+1] = temp;
                }
            }
        }
        String[][] newString = new String[items.length][6];

        for (int i = 0; i < newString.length; i++) {
            newString[i][4] = Double.toString(ar[i]);
        }
        for (int i = 0; i < newString.length; i++) {
            for (int j = 0; j < newString.length; j++) {
                if (Double.parseDouble(newString[i][4]) == (Double.parseDouble(items[j][4]))) {
                    newString[i][0] = items[j][0];
                    newString[i][1] = items[j][1];
                    newString[i][2] = items[j][2];
                    newString[i][3] = items[j][3];
                    newString[i][5] = items[j][5];
                }
            }
        }
        String column1 = "sid";
        String column2 = "code";
        String column3 = "desc";
        String column4 = "price";
        String column5 = "qty";
        String column6 = "cat";

        int boxWidth1 = 30;
        int boxWidth2 = 28;
        int boxWidth3 = 28;
        int boxWidth4 = 28;
        int boxWidth5 = 28;
        int boxWidth6 = 28;

        int padding1 = (boxWidth1 - column1.length() - 2) / 2;
        int padding2 = (boxWidth2 - column2.length() - 2) / 2;
        int padding3 = (boxWidth3 - column3.length() - 2) / 2;
        int padding4 = (boxWidth4 - column4.length() - 2) / 2;
        int padding5 = (boxWidth5 - column5.length() - 2) / 2;
        int padding6 = (boxWidth6 - column6.length() - 2) / 2;
        
		System.out.println();
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
        System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
        System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|", "", column3, "");
        System.out.printf("%-" + padding4 + "s%s%-" + padding4 + "s|", "", column4, "");
        System.out.printf("%-" + padding5 + "s%s%-" + padding5 + "s|", "", column5, "");
        System.out.printf("%-" + padding6 + "s%s%-" + padding6 + "s|%n", "", column6, "");
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------------+");

        for (int i = 0; i < newString.length; i++) {
            column1 = newString[i][1];
            column2 = newString[i][0];
            column3 = newString[i][3];
            column4 = newString[i][4];
            column5 = newString[i][5];
            column6 = newString[i][2];

            padding1 = (boxWidth1 - column1.length() - 2) / 2;
            padding2 = (boxWidth2 - column2.length() - 2) / 2;
            padding3 = (boxWidth3 - column3.length() - 2) / 2;
            padding4 = (boxWidth4 - column4.length() - 2) / 2;
            padding5 = (boxWidth5 - column5.length() - 2) / 2;
            padding6 = (boxWidth6 - column6.length() - 2) / 2;

            System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
            System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
            System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|", "", column3, "");
            System.out.printf("%-" + padding4 + "s%s%-" + padding4 + "s|", "", column4, "");
            System.out.printf("%-" + padding5 + "s%s%-" + padding5 + "s|", "", column5, "");
            System.out.printf("%-" + padding6 + "s%s%-" + padding6 + "s|%n", "", column6, "");
        }

        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("%n %s","Do you want to go stock management (y or n)");
        yOrN = input.next();
        if (yOrN.equals("y")||yOrN.equals("Y")){
            clearConsole();
            stockManage();
        }
    }

    public static void viewItems(){
        String welcomeText = "view items";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (items.length == 0) {
            System.out.println(" No items are found if you want to go stock management page (Y or N) : ");
            yOrN = input.next();
            if (yOrN.equals("Y")||yOrN.equals("y")) {
                clearConsole();
                stockManage();
            }else return;
        }

        String[] catogery = new String[0];
        boolean isDuplicate;

        for (int i = 0; i < items.length; i++) {
            String category = items[i][2];
            isDuplicate = false;

            for (int j = 0; j < catogery.length; j++) {
                if (category.equals(catogery[j])) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                String[] newAr = new String[catogery.length + 1];
                for (int k = 0; k < catogery.length; k++) {
                    newAr[k] = catogery[k];
                }
                newAr[catogery.length] = category;

                catogery = newAr;
            }
        }
        boolean flag = false;
        int l = 0;
        L1:
        do {
			System.out.println();
            System.out.println(catogery[l]+": ");

            String column1 = "sid";
            String column2 = "code";
            String column3 = "desc";
            String column4 = "price";
            String column5 = "qty";

            int boxWidth1 = 28;
            int boxWidth2 = 28;
            int boxWidth3 = 28;
            int boxWidth4 = 28;
            int boxWidth5 = 28;

            int padding1 = (boxWidth1 - column1.length() - 2) / 2;
            int padding2 = (boxWidth2 - column2.length() - 2) / 2;
            int padding3 = (boxWidth3 - column3.length() - 2) / 2;
            int padding4 = (boxWidth4 - column4.length() - 2) / 2;
            int padding5 = (boxWidth5 - column5.length() - 2) / 2;

            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
            System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
            System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|", "", column3, "");
            System.out.printf("%-" + padding4 + "s%s%-" + padding4 + "s|", "", column4, "");
            System.out.printf("%-" + padding5 + "s%s%-" + padding5 + "s|%n", "", column5, "");
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");

            for (int i = 0 ; i < items.length; i++) {
                if (catogery[l].equals(items[i][2])) {
                    column1 = items[i][1];
                    column2 = items[i][0];
                    column3 = items[i][3];
                    column4 = items[i][4];
                    column5 = items[i][5];

                    boxWidth1 = 28;
                    boxWidth2 = 28;
                    boxWidth3 = 28;
                    boxWidth4 = 28;
                    boxWidth5 = 28;

                    padding1 = (boxWidth1 - column1.length() - 2) / 2;
                    padding2 = (boxWidth2 - column2.length() - 2) / 2;
                    padding3 = (boxWidth3 - column3.length() - 2) / 2;
                    padding4 = (boxWidth4 - column4.length() - 2) / 2;
                    padding5 = (boxWidth5 - column5.length() - 2) / 2;

                    System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
                    System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
                    System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|", "", column3, "");
                    System.out.printf("%-" + padding4 + "s%s%-" + padding4 + "s|", "", column4, "");
                    System.out.printf("%-" + padding5 + "s%s%-" + padding5 + "s|%n", "", column5, "");
                }
            }
            System.out.println("+------------------------------------------------------------------------------------------------------------------------------------+");
            if (l < catogery.length -1){
                l++;
                continue L1;
            } else flag = true;
        }while (!flag);
        System.out.print("Do you want to go stock manager (Y or N): ");
        yOrN = input.next();
        if (yOrN.equals("y") || yOrN.equals("Y")){
            clearConsole();
            stockManage();
        }else return;
    }

    public static void searchItems() {
        String welcomeText = "search supplier";
        int padding = (boxWidth - welcomeText.length() - 2) / 2; 

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        boolean stopped = false;
        if (idName.length == 0){
            System.out.printf("%n %s","No suppliers are found please add suppliers. Do  you want to go stock manager(y or n)");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")) {
                clearConsole();
                stockManage();
            }else return;
        }
        do {
            System.out.printf("%n%s", "Enter supplier id: ");
            String id = input.next();
            String supplierName = null;

            int temp = -1;
            for (int i = 0; i < idName.length; i++) {
                if (id.equals(idName[i][0])) {
                    supplierName = idName[i][1];
                    break;
                }
            }
            if (supplierName != null) System.out.printf("%n%s%n", "Supplier name: " + supplierName);
            else if (supplierName == null) {
                System.out.println("Supplier id is not found! please try again...");
                continue;
            }
            String column1 = "item code";
            String column2 = "description";
            String column3 = "unit price";
            String column4 = "qty on hand";
            String column5 = "category";

            int boxWidth1 = 30;
            int boxWidth2 = 28;
            int boxWidth3 = 28;
            int boxWidth4 = 28;
            int boxWidth5 = 28;

            int padding1 = (boxWidth1 - column1.length() - 2) / 2;
            int padding2 = (boxWidth2 - column2.length() - 2) / 2;
            int padding3 = (boxWidth3 - column3.length() - 2) / 2;
            int padding4 = (boxWidth4 - column4.length() - 2) / 2;
            int padding5 = (boxWidth5 - column5.length() - 2) / 2;

            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
            System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
            System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|", "", column3, "");
            System.out.printf("%-" + padding4 + "s%s%-" + padding4 + "s|", "", column4, "");
            System.out.printf("%-" + padding5 + "s%s%-" + padding5 + "s|%n", "", column5, "");
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");

            boolean flag = false;
            int[] itemIndices = new int[0];

            for (int i = 0, j = 0; i < items.length; i++) {
                if (id.equals(items[i][1])) {
                    int[] yr = new int[itemIndices.length + 1];
                    for (int k = 0; k < itemIndices.length; k++) {
                        yr[k] = itemIndices[k];
                    }
                    itemIndices = yr;
                    itemIndices[j] = i;
                    j++;
                }
            }

            for (int j = 0; j < itemIndices.length; j++) {
                column1 = items[itemIndices[j]][0];
                column2 = items[itemIndices[j]][3];
                column3 = items[itemIndices[j]][5];
                column4 = items[itemIndices[j]][4];
                column5 = items[itemIndices[j]][2];

                boxWidth1 = 30;
                boxWidth2 = 28;
                boxWidth3 = 28;
                boxWidth4 = 28;
                boxWidth5 = 28;

                padding1 = (boxWidth1 - column1.length() - 2) / 2;
                padding2 = (boxWidth2 - column2.length() - 2) / 2;
                padding3 = (boxWidth3 - column3.length() - 2) / 2;
                padding4 = (boxWidth4 - column4.length() - 2) / 2;
                padding5 = (boxWidth5 - column5.length() - 2) / 2;

                System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
                System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
                System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|", "", column3, "");
                System.out.printf("%-" + padding4 + "s%s%-" + padding4 + "s|", "", column4, "");
                System.out.printf("%-" + padding5 + "s%s%-" + padding5 + "s|%n", "", column5, "");
            }

            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");

            System.out.printf("%n%s", "Do you want to search again (Y/N)? ");
            yOrN = input.next();

            if (yOrN.equals("n")||yOrN.equals("N")) {
                clearConsole();
                stockManage();
            }
        } while (!stopped);
    }

    public static void addItems(){
        String welcomeText = "Add items";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (itemsOnCategories.length == 0) {
            System.out.printf("%n %s %n %s","OOPS! It seems that you don't have any item categories in system","Do you want to add a new item category (y or n)");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")) {
                clearConsole();
                addNewItemCategory();
            }else {
                clearConsole();
                stockManage();
            }
        }else if(itemsOnCategories.length > 0) {
            if (idName.length == 0) {
                System.out.printf("%n %s %n %s","OOPS! It seems that you don't have any suppliers in system","Do you want to add suppliers (y or n)");
                yOrN = input.next();
                if (yOrN.equals("y")||yOrN.equals("Y")) {
                    clearConsole();
                    addSupplier();
                }else {
                    clearConsole();
                    stockManage();
                }
            }else {
                boolean stoping = false;
                L1:
                do {
                    System.out.printf("%n %s","Item code: ");
                    String id = input.next();

                    for (int j = 0; j < items.length; j++) {
                        if (id.equals(items[j][0])){
                            System.out.printf("%n %s","Oops this item is already have.please enter another item");
                            continue L1;
                        }
                    }
                    String[][] newAr = new String[items.length + 1][6];
                    for (int i = 0; i < items.length; i++) {
                        for (int j = 0; j < items[i].length; j++) {
                            newAr[i][j] = items[i][j];
                        }
                    }
                    items = newAr;
                    items[it][0] = id;

                    System.out.printf("%n %s %n","Supplier List");
                    String column1 = "#";
                    String column2 = "supplier id";
                    String column3 = "supplier name";

                    int boxWidth1 = 20;
                    int boxWidth2 = 28;
                    int boxWidth3 = 28;

                    int padding1 = (boxWidth1 - column1.length() - 2) / 2;
                    int padding2 = (boxWidth2 - column2.length() - 2) / 2;
                    int padding3 = (boxWidth3 - column3.length() - 2) / 2;

                    System.out.println("+----------------------------------------------------------------------+");
                    System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
                    System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
                    System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|%n", "", column3, "");
                    System.out.println("+----------------------------------------------------------------------+");

                    boolean flag = false;
                    int j = 0;
                    do {
                        column1 = ((j+1)+"");
                        column2 = idName[j][0];
                        column3 = idName[j][1];
                        boxWidth1 = 20;
                        boxWidth2 = 28;
                        boxWidth3 = 28;

                        padding1 = (boxWidth1 - column1.length() - 2) / 2;
                        padding2 = (boxWidth2 - column2.length() - 2) / 2;
                        padding3 = (boxWidth3 - column3.length() - 2) / 2;

                        System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
                        System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|", "", column2, "");
                        System.out.printf("%-" + padding3 + "s%s%-" + padding3 + "s|%n", "", column3, "");
                        if (j < idName.length - 1) j++;
                        else flag = true;
                    }while (!flag);

                    System.out.println("+----------------------------------------------------------------------+");
                    System.out.printf("%n %s","Enter the Supplier number: ");
                    int supplierNum = input.nextInt();
                    items[it][1] = idName[supplierNum - 1][0];

                    System.out.printf("%s %n","Items categories");
                    column1 = "#";
                    column2 = "Category name";
                    boxWidth1 = 20;
                    boxWidth2 = 28;

                    padding1 = (boxWidth1 - column1.length() - 2) / 2;
                    padding2 = (boxWidth2 - column2.length() - 2) / 2;

                    System.out.println("+-------------------------------------------+");
                    System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
                    System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|%n", "", column2, "");
                    System.out.println("+-------------------------------------------+");

                    flag = false;
                    int k = 0;
                    do {
                        column1 = (k+1)+"";
                        column2 = itemsOnCategories[k];
                        boxWidth1 = 20;
                        boxWidth2 = 28;

                        padding1 = (boxWidth1 - column1.length() - 2) / 2;
                        padding2 = (boxWidth2 - column2.length() - 2) / 2;

                        System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
                        System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|%n", "", column2, "");
                        if (k < itemsOnCategories.length - 1) k++;
                        else flag = true;
                    }while (!flag);
                    System.out.println("+------------------------------------------+");
                    System.out.printf("%n %s","Enter the Category number: ");
                    int categoryNum = input.nextInt();
                    items[it][2] = itemsOnCategories[categoryNum -1];
                    System.out.printf("%n %-10s %s","Discription",":");
                    String discription = input.next();
                    items[it][3] = discription;
                    System.out.printf("%n %-10s %s","Unit Price",":");
                    items[it][4] = input.next();
                    System.out.printf("%n %-10s %s","Qty on hand",":");
                    items[it][5] = input.next();
                    it++;

                    System.out.printf("%n %-10s %s","Added Successfully!","Do you want to add another item (y or n): ");
                    yOrN = input.next();
                    if (yOrN.equals("y")||yOrN.equals("Y")) {
						clearConsole();
						addItems();
					}
                    else if (yOrN.equals("n")||yOrN.equals("N")) {
                        clearConsole();
                        stockManage();
                        break;
                    }else break ;
                }while(!stoping);
            }
        }
    }

    public static void manageItemCategories(){
        String welcomeText = "manage item category";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        System.out.printf("%n %s %-50s %s %s %n %s %-50s %s %s %n %n","[1]","Add new Item Category","[2]","Delete Item Category","[3]","Update Item Category","[4]","Stock Management");

        System.out.print("Enter an option to Continue > ");
        int option = input.nextInt();

        if (option == 1) {
            clearConsole();
            addNewItemCategory();
        } else if (option == 2){
            clearConsole();
            deleteItemCategory();
        } else if (option == 3) {
            clearConsole();
            updateItemCategory();
        } else if (option == 4) {
            clearConsole();
            stockManage();
        }
    }

    public static void updateItemCategory(){
        String welcomeText = "Update item categories";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (itemsOnCategories.length == 0) {
            System.out.print("No item categories are found you want add item categories! (Y or N): ");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")) {
                clearConsole();
                addNewItemCategory();
            }else {
                clearConsole();
                manageItemCategories();
            }
        }
        L1:
        do {
            System.out.printf("%-10s","Item Category name: ");
            String name = input.next();
            boolean flag = false;
            int temp = -1;
            for (int i = 0; i < itemsOnCategories.length; i++) {
                if (name.equals(itemsOnCategories[i])) {
                    temp = i;
                    flag = true;
                }
            }
            if (flag) {
                System.out.printf("%n %-10s","Enter the new Item Category name: ");
                name = input.next();

                itemsOnCategories[temp] = name;
                System.out.printf("%n %s","Update SuccessFully! Do you want to update Another item category (y or n): ");
                yOrN = input.next();
                if (yOrN.equals("y")||yOrN.equals("Y")) continue L1;
                else if (yOrN.equals("n")||yOrN.equals("N")){
                    clearConsole();
                    manageItemCategories();
                }
            }else System.out.printf("%s %n","Cant'find item category. Try Again...");
        }while (!yOrN.equals("n")||!yOrN.equals("N"));
    }

    public static void deleteItemCategory(){
        String welcomeText = "delete item category";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (itemsOnCategories.length == 0) {
            System.out.print("No item categories are found you want add item categories! (Y or N): ");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")) {
                clearConsole();
                addNewItemCategory();
            }else {
                clearConsole();
                manageItemCategories();
            }
        }
        boolean flag = false;
        do {
            System.out.printf("%-10s","Item category name:");
            String name = input.next();
            int temp = -1;
            for (int i = 0; i < itemsOnCategories.length; i++) {
                if (name.equals(itemsOnCategories[i])) {
                    flag = true;
                    temp = i;
                    break;
                }
            }

            if (flag){
                if (temp != -1){
                    String[] ar = new String[itemsOnCategories.length - 1];

                    int newIndex = 0;
                    for (int i = 0; i < itemsOnCategories.length; i++) {
                        if (i != temp) {
                            ar[newIndex] = itemsOnCategories[i];
                            newIndex++;
                        }
                    }
                    itemsOnCategories = ar;
                    cat = cat - 1;
                }
                System.out.printf("%-10s","Delete Successfully! Do you want delete another (y or n)");
                yOrN = input.next();
                if (yOrN.equals("y")||yOrN.equals("Y")){
                    clearConsole();
                    deleteItemCategory();
                }
                else if (yOrN.equals("n")||yOrN.equals("N")){
                    clearConsole();
                    manageItemCategories();
                }else break;
            }else System.out.printf("%s %n","Cant'find Item Category. Try Again...");
        }while (!yOrN.equals("n")||!yOrN.equals("N"));
    }

    public static void addNewItemCategory(){
        String welcomeText = "add new item category";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        String yOrN = "a";

        L1:
        do {
            System.out.printf("%n %-18s","Enter the new item category:  ");
            String itemCategory = input.next();
            for (int i = 0; i < itemsOnCategories.length; i++) {
                if (itemCategory.equals(itemsOnCategories[i])){
                    System.out.printf("%s %n","Already have this item category. Try Again...");
                    continue L1;
                }
            }
            String[] ar = new String[itemsOnCategories.length + 1];
            for (int i = 0; i < itemsOnCategories.length; i++) {
                ar[i] = itemsOnCategories[i];
            }

            itemsOnCategories = ar;
            itemsOnCategories[cat] = itemCategory;
            cat++;
            System.out.printf("%n %s","added successfully! Do you want to add another category (y or n)");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")) {
				clearConsole();
				addNewItemCategory();
			}
            else if (yOrN.equals("n")||yOrN.equals("N")){
                clearConsole();
                manageItemCategories();
            }
        }while(!yOrN.equals("n")||yOrN.equals("N"));
    }

    public static void changeCredentianals(){
        String welcomeText = "credentianal manage";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        boolean flag = false;
        L1:
        do {
            System.out.printf("%-10s","Please enter the user name to verify it's you: ");
            String name = input.next();
            if (name.equals(username)){
                System.out.printf("%n %s","Hey "+username);
                L2:
                do {
                    System.out.printf("%n %s","Enter your current Password : ");
                    String pw = input.next();
                    if (pw.equals(password)){
                        flag = true;
                        System.out.printf("%n %s","Enter Your new password : ");
                        password = input.next();
                        System.out.printf("%n %s","Password changed Successfully! Do you want to go home page. (y or n): ");
                        yOrN = input.next();
                        if (yOrN.equals("y")||yOrN.equals("Y")){
                            clearConsole();
                            homePage();
                        }else return;
                    }else {
                        System.out.printf("%n %s","Incorrect password...");
                        continue L2;
                    }
                }while(!flag);
            }else {
                System.out.println("Incorrect User name...");
                continue L1;
            }
        }while (!flag);
    }

    public static void supplierManage(){
        String welcomeText = "supplier manage";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        System.out.printf("%n %s %-50s %s %s %n %s %-50s %s %s %n %s %-50s %s %s %n %n","[1]","Add Supplier","[2]","Update Supplier","[3]","Delete Supplier","[4]","View Supplier","[5]","Search Supplier","[6]","Home Page");

        System.out.print("Enter an option to Continue > ");
        int option = input.nextInt();

        if(option == 1){
            clearConsole();
            addSupplier();
        } else if (option == 2) {
            clearConsole();
            updateSupplier();
        } else if (option == 3) {
            clearConsole();
            deleteSupplier();
        } else if (option == 4) {
            clearConsole();
            viewSupplier();
        } else if (option == 5) {
            clearConsole();
            searchSupplier();
        } else if (option == 6) {
            clearConsole();
            homePage();
        }
    }

    public static void searchSupplier(){
        String welcomeText = "search supplier";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (idName.length == 0){
            System.out.printf("%n %s","No suppliers! Do you want to go supplier manage page (y or n): ");
            yOrN = input.next();
            if (yOrN.equals("y")){
                clearConsole();
                supplierManage();
            }else return;
        }
        L1:
        do {
            System.out.printf("%n %-10s","Supplier id:");
            String id = input.next();
            boolean found = false;
            for (int i = 0; i < idName.length; i++) {
                if (idName[i][0].equals(id)) {
                    System.out.printf("%n %-10s","Supplier name:" + idName[i][1]);
                    System.out.println();
                    found = true;
                    break;
                }
            }
            if (!found){
                System.out.println("Can't find Supplier id! Try Again...");
                continue L1;
            }
            System.out.print("Search Successfully! Do you want to find another (y or n): ");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")) continue L1;
            else if(yOrN.equals("n")||yOrN.equals("N")) {
                clearConsole();
                supplierManage();
            }else break;
        }while(!yOrN.equals("n")||!yOrN.equals("N"));
    }

    public static void viewSupplier(){
        String welcomeText = "view suppliers";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.println();

        if (idName.length == 0){
            System.out.printf("%n %s","No suppliers! Do you want to go supplier manage page (y or n): ");
            yOrN = input.next();
            if (yOrN.equals("y")){
                clearConsole();
                supplierManage();
            }else return;
        }

        String column1 = "supplier id";
        String column2 = "supplier name";

        int boxWidth1 = 28;
        int boxWidth2 = 28;

        int padding1 = (boxWidth1 - column1.length() - 2) / 2;
        int padding2 = (boxWidth2 - column2.length() - 2) / 2;

        System.out.println("+----------------------------------------------------+");
        System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
        System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|%n", "", column2, "");
        System.out.println("+----------------------------------------------------+");

        boolean flag = false;
        int i = 0;
        do {
            column1 = idName[i][0];
            column2 = idName[i][1];

            boxWidth1 = 28;
            boxWidth2 = 28;

            padding1 = (boxWidth1 - column1.length() - 2) / 2;
            padding2 = (boxWidth2 - column2.length() - 2) / 2;

            System.out.printf("|%-" + padding1 + "s%s%-" + padding1 + "s|", "", column1, "");
            System.out.printf("%-" + padding2 + "s%s%-" + padding2 + "s|%n", "", column2, "");
            if (i < idName.length - 1) i++;
            else flag = true;
        }while (!flag);

        System.out.println("+----------------------------------------------------+");
        System.out.print("Do you want to go Supplier Manage page (y or n): ");
        yOrN = input.next();
        if (yOrN.equals("y")||yOrN.equals("Y")){
            clearConsole();
            supplierManage();
        }else return;
    }

    public static void  deleteSupplier(){
        String welcomeText = "delete Supplier";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (idName.length == 0){
            System.out.printf("%n %s","No suppliers! Do you want to go supplier manage page (y or n): ");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")){
                clearConsole();
                supplierManage();
            }else return;
        }
        boolean flag = false;
        do {
            System.out.printf("%-10s","Supplier id:");
            String name = input.next();
            int temp = -1;
            for (int i = 0; i < idName.length; i++) {
                for (int j = 0; j < idName[i].length; j++) {
                    if (name.equals(idName[i][0])) {
                        flag = true;
                        temp = i;
                        break;
                    }
                }
            }

            if (flag){
                if (temp != -1){
                    String[][] ar = new String[idName.length - 1][2];
                    int newIndex = 0;
                    for (int i = 0; i < idName.length; i++) {
                        if (i != temp) {
                            ar[newIndex][0] = idName[i][0];
                            ar[newIndex][1] = idName[i][1];
                            newIndex++;
                        }
                    }
                    idName = ar;
                    sup = sup - 1;
                }
                System.out.printf("%-10s","Delete Successfully! Do you want delete another (y or n)");
                yOrN = input.next();
                if (yOrN.equals("y")||yOrN.equals("Y")) {
                    clearConsole();
                    deleteSupplier();
                }
                else if (yOrN.equals("n")||yOrN.equals("N")){
                    clearConsole();
                    supplierManage();
                }else break;
            }else System.out.printf("%s %n","Cant'find supplier. Try Again...");
        }while (!yOrN.equals("n")||!yOrN.equals("N"));
    }

    public static void updateSupplier() {
        String welcomeText = "Update Supplier";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        if (idName.length == 0){
            System.out.printf("%n %s","No suppliers! Do you want to go supplier manage page (y or n): ");
            yOrN = input.next();
            if (yOrN.equals("y")||yOrN.equals("Y")){
                clearConsole();
                supplierManage();
            }else return;
        }
        L1:
        do {
            System.out.printf("%n %-10s","Supplier id: ");
            String id = input.next();
            boolean flag = false;
            int temp = -1;
            for (int i = 0; i < idName.length; i++) {
                for (int j = 0; j < idName[i].length ; j++) {
                    if (id.equals(idName[i][0])) {
                        temp = i;
                        flag = true;
                    }
                }
            }
            if (flag) {
                System.out.printf("%-10s %n","Supplier name: "+idName[temp][1]);
                System.out.printf("%n %-10s","Enter the new Supplier name: ");
                String name = input.next();

                idName[temp][1] = name;
                System.out.printf("%n %s","Update SuccessFully! Do you want to update Another Supplier (y or n): ");
                yOrN = input.next();
                if (yOrN.equals("y")||yOrN.equals("Y")) continue L1;
                else if (yOrN.equals("n")||yOrN.equals("N")){
                    clearConsole();
                    supplierManage();
                }
            }else System.out.printf("%s","Cant'find Supplier Id. Try Again...");
        }while (!yOrN.equals("n")||!yOrN.equals("N"));
    }

    public static void addSupplier(){
        String welcomeText = "add suplier";
        int padding = (boxWidth - welcomeText.length() - 2) / 2;

        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|%-" + padding + "s%S%-" + padding + "s|%n", "", welcomeText, "");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        L1:
        do {
            boolean flag = false;
            System.out.printf("%n %-15s","Supplier Id: ");
            String id = input.next();
            for (int i = 0 ; i < idName.length ; i++){
                if (id.equals(idName[i][0])) flag = true;
            }
            if (!flag){
                String[][] idN = new String[idName.length + 1][2];
                for (int i = 0; i < idName.length; i++) {
                    for (int j = 0; j < idName[i].length; j++) {
                        idN[i][0] = idName[i][0];
                        idN[i][1] = idName[i][1];
                    }
                }
                idName = idN;
                idName[sup][0] = id;

                System.out.printf(" %s","Supplier name: ");
                String name = input.next();
                idName[sup][1] = name;
                sup++;
                System.out.printf("%s ","Added Successfully... Do you want to add another Supplier (Y or N): ");
                yOrN = input.next();
                if (yOrN.equals("Y")||yOrN.equals("y")) {
					clearConsole();
					addSupplier();
                }else if(yOrN.equals("N")||yOrN.equals("n")){
                    clearConsole();
                    supplierManage();
                } else break L1;
            }else{
                System.out.println("Already exists. try another supplier id! ");
                continue;
            }
        }while (!yOrN.equals("n")||yOrN.equals("N"));
    }

    public static void main(String[] args) {
        loginPage();
    }
}

