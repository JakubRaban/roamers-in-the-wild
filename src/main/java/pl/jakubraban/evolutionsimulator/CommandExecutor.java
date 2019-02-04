package pl.jakubraban.evolutionsimulator;

public class CommandExecutor {

    private World world;

    public CommandExecutor(World world) {
        this.world = world;
    }

    public void execute(String command) {
        parse(command);
    }

    private void parse(String command) {
        String[] splitCommand = command.split(" ");
        String commandName = splitCommand[0];
        String argument = "";
        if(splitCommand.length > 1) argument = splitCommand[1];
        switch (commandName.toLowerCase()) {
            case "delay":
                changeDelay(argument);
                break;
            case "restart":
                restartGame();
                break;
            case "retry" :
                bringLife();
                break;
        }
    }

    private void changeDelay(String newDelay) {
        try {
            int delay = Integer.parseInt(newDelay);
            if(delay >= 0) world.setDelay(delay);
        } catch (NumberFormatException nfe) {
            System.out.println("Zły argument");
        }
    }

    private void restartGame() {
        world.restartGame();
    }

    private void bringLife() {
        world.bringLife();
    }

}
