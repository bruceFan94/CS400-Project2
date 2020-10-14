// --== CS400 File Header Information ==--
// Name: Xiaochen Fan
// Email: xfan72@wisc.edu
// Team: NC
// Front End Developer
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StoreDriver {
  private Store walgreen;

  public StoreDriver() {
    walgreen = new Store();
  }

  public void commandsKey(String command) {
    Scanner sc = new Scanner(command);
    String[] commands = new String[3];
    int index = 0;
    while (sc.hasNext()) {
      if (index == 3) {
        System.out.println("There can be most three commands");
        return;
      }
      commands[index++] = sc.next();
    }
    try {
      switch (commands[0].toLowerCase()) {
        case "c":
          walgreen.clear();
          System.out.println("The Store is all cleared!");
          break;

        case "a":
          try {
            walgreen.add(commands[1], Double.parseDouble(commands[2]));
            System.out.println("Add " + commands[1]);
          } catch (NumberFormatException | NullPointerException npe) {
            System.out.println("Please type with the following way: <a> <name> <price>");
          } catch (IllegalArgumentException iae) {
            System.out.println("Can't adding a price already existed!");
          }
          break;

        case "af":
          try {
            walgreen.addFile(commands[1]);
          } catch (NullPointerException npe) {
            System.out.println("Please enter a file name");
          }
          break;

        case "lp":
          try {
            if (walgreen.lookUp(Double.parseDouble(commands[1])) == null) {
              System.out.println("No preduct has been found whose price is: " + commands[1]);
            } else {
              System.out.println(walgreen.lookUp(Double.parseDouble(commands[1])));
            }
          } catch (NoSuchElementException | NullPointerException nse) {
            System.out.println("No preduct has been found whose price is: " + commands[1]);
          }

          break;

        case "ln":
          try {
            if (walgreen.lookUp(walgreen.lookUp(commands[1])) == null) {
              System.out.println("No preduct has been found which name is: " + commands[1]);
            } else {
              System.out.println(walgreen.lookUp(commands[1]));
            }
          } catch (NoSuchElementException | NullPointerException nse) {
            System.out.println("No preduct has been found which name is: " + commands[1]);
          }

          break;

        case "lmax":
          try {
            System.out.println(walgreen.lookMax());
          } catch (NullPointerException nse) {
            System.out.println("This store is empty!");
          }
          break;

        case "lmin":
          try {
            System.out.println(walgreen.lookMin());
          } catch (NullPointerException nse) {
            System.out.println("This store is empty!");
          }
          break;

        case "b":
        case "q":
          System.out.println("Thank you for using the program!");
          break;


        default:
          System.out.println("SORRY, your command cannot be recognized: " + command);
          break;
      }
    } catch (Exception e) {
      System.out.println("Unexpected exception happens: ");
      e.printStackTrace();
      System.exit(0);
    }
  }

  /**
   * run method to start the program and keeps it running until user enter 'q';
   * 
   * @param sc Scanner object
   */
  public void run(Scanner sc) {
    String command = "None";
    while (!command.equals("q")) {
      keyMenu();
      command = sc.nextLine().trim();
      commandsKey(command);
    }
  }

  /**
   * private helper method to display the menu
   */
  private void keyMenu() {
    System.out.println(
        "**********************************************************************************************************\n"
            + "* Welcome to our new Store, here are the commands you can do:\n"
            + "* \"c\" clear the restaurants\n"
            + "* \"a <name> <price>\" adding a product with its name in String and price in double\n"
            + "* \"af <fileName>\" adding a file which has tons of products, format for each product must be <name, price>\n"
            + "* \"lp\" look up the product in the store by this given price\n"
            + "* \"ln\" look up the price of the product in the store by this given name\n"
            + "* \"lmax\" show the product which has the highest price \n"
            + "* \"lmin\" show the product which has the lowest price\n"
            + "* \"b\" back to this menu\n" + "* \"q\" quit the program\n"
            + "* WARNING: YOU HAVE TO ENTER THE CORRECT TYPE, AND THE COMMANDS ARE NOT CASE SENSITIVE!\n"
            + "* AND THE NAME OF THE RESTAURANT SHOULD NOT CONTAIN SPACE!\n"
            + "**********************************************************************************************************");
  }

  public static void main(String[] args) {
    new StoreDriver().run(new Scanner(System.in));
  }
}


