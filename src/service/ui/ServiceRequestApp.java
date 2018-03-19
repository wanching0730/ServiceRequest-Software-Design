package service.ui;

import service.domain.AppController;

public class ServiceRequestApp {

    private static IUserInterface userInterface;
    private static AppController controller;

    public static void main(String[] args) {
        controller = new AppController();
        userInterface = new AppUI();
        userInterface.setController(controller);
        userInterface.start();
    }
}
