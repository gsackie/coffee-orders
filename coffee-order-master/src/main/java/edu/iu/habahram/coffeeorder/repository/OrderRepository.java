package edu.iu.habahram.coffeeorder.repository;

import edu.iu.habahram.coffeeorder.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    public Receipt add(OrderData order) throws Exception {
        Beverage beverage = null;
        switch (order.beverage().toLowerCase()) {
            case "dark roast":
                beverage = new DarkRoast();
                break;
            case "HouseBlend":
                beverage = new HouseBlend();
                break;
                case"Expresso":
                beverage = new Expresso();
        }
        if (beverage == null) {
            throw new Exception("Beverage type '%s' is not valid!".formatted(order.beverage()));
        }
        for(String condiment : order.condiments()) {
            switch (condiment.toLowerCase()) {
                case "milk":
                   beverage = new Milk(beverage);
                   break;
                case "mocha":
                    beverage = new Mocha(beverage);
                    break;

                case "Soy":
                    beverage = new Soy(beverage);
                    break;

                case "whip":
                    beverage = new Whip(beverage);
                    break;

                default:
                    throw new Exception("Condiment type '%s' is not valid".formatted(condiment));
            }
        }
        Receipt receipt = new Receipt(beverage.getDescription(), beverage.cost());
        return receipt;
    }
}
