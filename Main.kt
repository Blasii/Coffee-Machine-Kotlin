package machine


fun main() {
    val coffeeMachine = CoffeeMachine()
    print("Write action (buy, fill, take, remaining, exit):\n")

    do {
        val input = readln()
        coffeeMachine.processInput(input)
    } while (coffeeMachine.processExit(input))
}