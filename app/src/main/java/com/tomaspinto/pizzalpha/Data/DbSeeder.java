package com.tomaspinto.pizzalpha.Data;

import com.tomaspinto.pizzalpha.Category;

public class DbSeeder {

    public static void seedDb(AppRepository db)
    {

        if(db.getTableList().size() == 0)
        {
             // Create Tables
             for (int i = 1; i <= 9; i++) {
                  Table table1 = new Table();
                  table1.tableName = "Table " + i;
                  db.insert(table1);
             }

             // Create Ingredients List
             Ingredient ing1 = new Ingredient(){{name = "Tomato Sauce";cost = 0;}};
             db.insert(ing1);
             Ingredient finalIng1 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing2 = new Ingredient(){{name = "Cheese";cost = 0;}};
             db.insert(ing2);
             Ingredient finalIng2 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing3 = new Ingredient(){{name = "Pepperoni";cost = 0;}};
             db.insert(ing3);
             Ingredient finalIng3 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing4 = new Ingredient(){{name = "Olives";cost = 0;}};
             db.insert(ing4);
             Ingredient finalIng4 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing5 = new Ingredient(){{name = "Mushrooms";cost = 0;}};
             db.insert(ing5);
             Ingredient finalIng5 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing6 = new Ingredient(){{name = "Tomatoes";cost = 0;}};
             db.insert(ing6);
             Ingredient finalIng6 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing7 = new Ingredient(){{name = "Ham";cost = 0;}};
             db.insert(ing7);
             Ingredient finalIng7 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing8 = new Ingredient(){{name = "BBQ Sauce";cost = 0;}};
             db.insert(ing8);
             Ingredient finalIng8 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing9 = new Ingredient(){{name = "Pineapple";cost = 0;}};
             db.insert(ing9);
             Ingredient finalIng9 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             Ingredient ing10 = new Ingredient(){{name = "Onions";cost = 0;}};
             db.insert(ing10);
             Ingredient finalIng10 = db.getIngredientList().get(db.getIngredientList().size() - 1);

             // Create Extra Ingredients
             Ingredient ing11 = new Ingredient(){{type="EXTRA"; name = "Extra Cheese";cost = 0.5;}};
             db.insert(ing11);
             Ingredient ing12 = new Ingredient(){{type="EXTRA"; name = "Extra Mushrooms";cost = 1;}};
             db.insert(ing12);
             Ingredient ing13 = new Ingredient(){{type="EXTRA"; name = "Extra Ham";cost = 1.5;}};
             db.insert(ing13);
             Ingredient ing14 = new Ingredient(){{type="EXTRA"; name = "Extra Pepperoni";cost = 2;}};
             db.insert(ing14);
             Ingredient ing15 = new Ingredient(){{type="EXTRA"; name = "Extra Olives";cost = 1.5;}};
             db.insert(ing15);

             // Create Products List and assign ingredients to pizzas
             Product margarita = new Product(){
             {
             name = "Margherita";
             basePrice = 9.99;
             tax = Math.round(basePrice) * 0.1;
             category = Category.PIZZAS;
             }
             };
             db.insert(margarita);

             // Tomato Sauce, cheese
             Product finalMargarita = db.getProductList().get(db.getProductList().size() - 1);;
             ProductIngredient pi1 = new ProductIngredient(){{product= finalMargarita; ingredient= finalIng1;}};
             ProductIngredient pi2 = new ProductIngredient(){{product= finalMargarita; ingredient= finalIng2;}};
             db.insert(pi1); db.insert(pi2);

             Product hawaii = new Product(){
             {
             name = "Hawaiian Pizza";
             basePrice = 16.99;
             tax = Math.round(basePrice) * 0.1;
             category = Category.PIZZAS;
             }
             };
             db.insert(hawaii);

             Product finalHawaii = db.getProductList().get(db.getProductList().size() - 1);
             // Tomato Sauce, cheese, ham, pineapple
             ProductIngredient pi3 = new ProductIngredient(){{product=finalHawaii; ingredient=finalIng1;}};
             ProductIngredient pi4 = new ProductIngredient(){{product=finalHawaii; ingredient=finalIng2;}};
             ProductIngredient pi5 = new ProductIngredient(){{product=finalHawaii; ingredient=finalIng7;}};
             ProductIngredient pi6 = new ProductIngredient(){{product=finalHawaii; ingredient=finalIng9;}};
             db.insert(pi3); db.insert(pi4); db.insert(pi5); db.insert(pi6);

             Product bbq = new Product(){
             {
             name = "BBQ Suprema";
             basePrice = 18.99;
             tax = Math.round(basePrice) * 0.1;
             category = Category.PIZZAS;
             }
             };
             db.insert(bbq);

             Product finalBBQ = db.getProductList().get(db.getProductList().size() - 1);
             // BBQ Sauce, cheese, Pepperoni, Mushrooms, onion
             ProductIngredient pi7 = new ProductIngredient(){{product=finalBBQ; ingredient=finalIng1;}};
             ProductIngredient pi8 = new ProductIngredient(){{product=finalBBQ; ingredient=finalIng2;}};
             ProductIngredient pi9 = new ProductIngredient(){{product=finalBBQ; ingredient=finalIng3;}};
             ProductIngredient pi10 = new ProductIngredient(){{product=finalBBQ; ingredient=finalIng5;}};
             ProductIngredient pi11 = new ProductIngredient(){{product=finalBBQ; ingredient=finalIng10;}};
             db.insert(pi7); db.insert(pi8); db.insert(pi9); db.insert(pi10); db.insert(pi11);

             Product coke = new Product(){
             {
             name = "Coke";
             basePrice = 1.59;
             size = "0.33cL";
             tax = Math.round(basePrice) * 0.1;
             category = Category.DRINKS;
             }
             };
             db.insert(coke);

             Product water = new Product(){
             {
             name = "Water";
             size = "0.5cL";
             basePrice = 1.79;
             tax = Math.round(basePrice) * 0.1;
             category = Category.DRINKS;
             }
             };
             db.insert(water);

             Product orange = new Product(){
             {
             name = "Orange Juice";
             size = "0.33cL";
             basePrice = 1.99;
             tax = Math.round(basePrice) * 0.1;
             category = Category.DRINKS;
             }
             };
             db.insert(orange);
        }
    }
}
