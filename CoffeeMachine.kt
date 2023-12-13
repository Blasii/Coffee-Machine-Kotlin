package machine

/**
 * Class of a coffee machine
 */
class CoffeeMachine(
    private var water: Int = 400,
    private var milk: Int = 540,
    private var coffeeBeans: Int = 120,
    private var disposableCups: Int = 9,
    private var money: Int = 550
) {

    // Enumeration representing the state of the coffee machine
    enum class State {
        CHOOSING_ACTION, CHOOSING_COFFEE, FILLING, EXIT
    }

    // Initial state
    private var currentState: State = State.CHOOSING_ACTION

    // Method to process user input
    fun processInput(input: String) {
        when (currentState) {
            State.CHOOSING_ACTION -> processChoosingAction(input)
            State.CHOOSING_COFFEE -> processChoosingCoffee(input)
            State.FILLING -> processFilling(input)
            State.EXIT -> processExit(input)
        }
    }

    //Process exit state
    fun processExit(input: String): Boolean {
        return input != "exit"
    }

    // Process choosing action state
    private fun processChoosingAction(input: String) {
        when (input) {
            "remaining" -> printRemaining()
            "buy" -> {
                currentState = State.CHOOSING_COFFEE
                println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to machine.main menu:")
            }

            "fill" -> {
                currentState = State.FILLING
                println("\nWrite how many ml of water you want to add:")
            }

            "take" -> {
                take()
                //printRemaining()
            }

            "exit" -> currentState = State.EXIT
        }
    }

    // Process choosing coffee state
    private fun processChoosingCoffee(input: String) {
        when (input) {
            "1" -> makeCoffee(250, 0, 16, 4)
            "2" -> makeCoffee(350, 75, 20, 7)
            "3" -> makeCoffee(200, 100, 12, 6)
            "back" -> {
                currentState = State.CHOOSING_ACTION
                println("\nWrite action (buy, fill, take, remaining, exit):")
            }
        }
        currentState = State.CHOOSING_ACTION
    }

    // Process filling state
    private fun processFilling(input: String) {
        water += input.toInt()
        println("Write how many ml of milk you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        coffeeBeans += readln().toInt()
        println("Write how many disposable cups you want to add:")
        disposableCups += readln().toInt()
        currentState = State.CHOOSING_ACTION
        println("\nWrite action (buy, fill, take, remaining, exit):")
    }

    // Helper method to make coffee
    private fun makeCoffee(waterNeeded: Int, milkNeeded: Int, coffeeBeansNeeded: Int, cost: Int) {
        when {
            water < waterNeeded -> println("Sorry, not enough water!\n\nWrite action (buy, fill, take, remaining, exit):")
            milk < milkNeeded -> println("Sorry, not enough milk!\n\nWrite action (buy, fill, take, remaining, exit):")
            coffeeBeans < coffeeBeansNeeded -> println("Sorry, not enough coffee beans!\n\nWrite action (buy, fill, take, remaining, exit):")
            disposableCups <= 0 -> println("Sorry, not enough disposable cups!\n\nWrite action (buy, fill, take, remaining, exit):")
            else -> {
                println("I have enough resources, making you a coffee!\n")
                water -= waterNeeded
                milk -= milkNeeded
                coffeeBeans -= coffeeBeansNeeded
                disposableCups--
                money += cost
                println("Write action (buy, fill, take, remaining, exit):")
            }
        }
    }

    // Helper method to take money
    private fun take() {
        println("\nI gave you $$money")
        money = 0
        println("\nWrite action (buy, fill, take, remaining, exit):")
    }

    // Helper method to print remaining resources
    private fun printRemaining() {
        println("\nThe coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffeeBeans g of coffee beans")
        println("$disposableCups disposable cups")
        println("$$money of money\n")
        println("Write action (buy, fill, take, remaining, exit):")
    }
}