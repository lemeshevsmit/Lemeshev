package com.intellias.lemeshev;

import com.intellias.lemeshev.exception.IncorrectIdException;
import com.intellias.lemeshev.exception.IncorrectInputParameterException;
import com.intellias.lemeshev.exception.NoMoneyException;
import com.intellias.lemeshev.middle_level.EmptyShop;
import com.intellias.lemeshev.start_level.Shop;

public class Main {

    public static void main(String[] args) {
        EmptyShop shop = new EmptyShop();
        boolean exit = false;
        shop.displayMenu();
        while (!exit) {
            try {
                exit = shop.isExit(exit);
            } catch (NoMoneyException | IncorrectIdException | IncorrectInputParameterException e) {
                System.out.println("***************************************************");
            }
        }
    }
}
