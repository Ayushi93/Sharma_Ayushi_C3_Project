import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest {
    Restaurant restaurant;

    List<Item> items = new ArrayList<>();

    //REFACTORING ALL THE REPEATED LINES OF CODE

    public void addRestaurantDetails() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable Lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
       addRestaurantDetails();
       restaurant.setClosingTime(LocalTime.now().plusMinutes(5));
       restaurant.isRestaurantOpen();
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        addRestaurantDetails();
        restaurant.setClosingTime(LocalTime.now().minusMinutes(5));
        restaurant.isRestaurantOpen();

    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        addRestaurantDetails();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

   @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addRestaurantDetails();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable Lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addRestaurantDetails();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

//<<<<<<<<<<<<<Adding the feature to calculate cost of the items selected from menu>>>>>>>>>>>>>>>>>>>>

    @Test
    public void order_cost_should_display_the_cumulative_cost_of_the_selected_items(){
        addRestaurantDetails();

        //Fetching the list of items from the menu..
        items = restaurant.getMenu();

        //Getting the cost of the items selected using getOrderCost Method.
        assertEquals(388, restaurant.getOrderCost(items));
    }

    @Test
    public void order_cost_should_reduce_the_cumulative_cost_when_an_item_is_deselected(){
        addRestaurantDetails();

        //Fetching the list of items from the menu..
        items = restaurant.getMenu();

        //Getting the cost before deselecting
        int currentCost = restaurant.getOrderCost(items);

        //Getting the cost of item to be deselected
        int costDeselectedItem = items.get(1).getPrice();

        //Deselecting the item
        items.remove(1);

        //Assertion
        assertEquals(currentCost - costDeselectedItem, restaurant.getOrderCost(items));
    }

}