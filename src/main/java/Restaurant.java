import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //Getting the Current time
        LocalTime currentLocalTime = getCurrentTime();

        //Getting the opening time of Restaurant
        LocalTime openingTime = getOpeningTime();

        //Getting the Closing time of Restaurant
        LocalTime closingTime = getClosingTime();

        //Checking, If the current time falls between opening & closing time of a restaurant..
        if(openingTime.isAfter(currentLocalTime) && closingTime.isBefore(currentLocalTime)){
              return true;
        }
        //Checking, If the current time is equal to opening or closing time of a restaurant..
        else return openingTime.equals(currentLocalTime) || closingTime.equals(currentLocalTime);
    }

    public LocalTime getCurrentTime(){
        return  LocalTime.now();
    }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int getOrderCost(List<Item> items){
        int totalCost = 0;
        for(Item allItems : items){
            totalCost += allItems.getPrice();
        }
        return totalCost;
    }

}
