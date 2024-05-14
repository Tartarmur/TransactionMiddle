package ru.netology

fun main() {
    var comissionLimit = checkLimit(150_000, 600_000, 0, 0, 150_000)
    var comissionType = checkTypeCard("Master Card", 35, 0.0075, 0.006,20, 75_000, comissionLimit , 150_000)
}

fun checkLimit (limitDays: Int, limitMonth: Int, previousOperationsMonth: Int, previousOperationsDay: Int, amountTransaction : Int) : Boolean {
    var possibleTrascation = when {
        previousOperationsMonth + amountTransaction > limitMonth || amountTransaction + previousOperationsDay > limitDays -> false
        else -> true
    }
    return possibleTrascation
}

fun checkTypeCard (type: String , minComissionVisa: Int, percentComissionVisa: Double, percentComissionMC: Double,
                   addComissionMC: Int, limitMasterCardMonth: Int, possibleTrascation : Boolean , amountTransaction : Int) {
    when (possibleTrascation) {
        false -> println("Операция не может быть выполнена, так как превышен лимит. За подробностями обратитесь, пожалуйста, в ваш банк")
        else -> when (type) {
            "Visa" -> when {
                (amountTransaction * percentComissionVisa).toInt() > minComissionVisa -> println("Cумма комиссии за эту операцию по данной карте составит: " + (amountTransaction * percentComissionVisa).toInt() + "руб.")
                else -> println("Cумма комиссии за эту операцию по данной карте составит: " + minComissionVisa + "руб.")
            }
            "Master Card" -> when {
                amountTransaction <= limitMasterCardMonth -> println("Cумма комиссии за эту операцию по данной карте составит: 0 руб.")
                else -> println(("Cумма комиссии за эту операцию по данной карте составит: " + (((amountTransaction - limitMasterCardMonth) * percentComissionMC).toInt() + addComissionMC) + " руб"))
            }
            else -> println("Cумма комиссии за эту операцию по данной карте составит: 0 руб.")
        }
    }

}


