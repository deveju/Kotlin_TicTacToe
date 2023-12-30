import java.util.*

var game = arrayOf(
    arrayOf("1", "2", "3"),
    arrayOf("4", "5", "6"),
    arrayOf("7", "8", "9")
)

var roundNum = 1
var notOver = true

fun main() {
    var mark = ""
    while (true) {
        print("PLAY AS X OR CIRCLE 》》 ")
        mark = readLine()?.trim()?.toLowerCase() ?: ""
        if (mark.startsWith('c')) {
            mark = "XO"
            break
        } else if (mark.startsWith('x')) {
            mark = "OX"
            break
        } else {
            println("Invalid input!")
        }
    }

    while (notOver) {
        roundScreen()

        while (true) {
            print("》》 ")
            val play = readLine()?.trim() ?: ""
            if (play.startsWith('1') || play.startsWith('2') || play.startsWith('3') ||
                play.startsWith('4') || play.startsWith('5') || play.startsWith('6') ||
                play.startsWith('7') || play.startsWith('8') || play.startsWith('9')
            ) {
                marking(game, (play.toInt() - 1) / 3, (play.toInt() - 1) % 3, mark)
                roundNum++
                notOver = notOverVerification()
                roundScreen()

                if (!notOver) {
                    break
                }
            }

            if (!notOver) {
                break
            }

            enemyPlay(mark)
            roundNum++
            notOver = notOverVerification()
            roundScreen()

            if (roundNum >= 10) {
                notOver = false
                break
            }
        }

        if (roundNum >= 10 && !notOver) {
            break
        }
    }

    if (!notOver) {
        println("\n》》 Game over! Game is a draw.")
        return
    }

    println("\n》》 Game over! There is a winner.")
}

fun roundScreen() {
    println("\n》》 ROUND $roundNum 《《─────────────\n")

    for ((i, x) in game.withIndex()) {
        print("          ")
        println(x.joinToString("  ║  "))
        if (i == game.size - 1) break
        print("         ")
        println("═══════════════")
    }
}

fun marking(arrayEx: Array<Array<String>>, x: Int, y: Int, mark: String) {
    while (true) {
        if (arrayEx[x][y] !in listOf("X", "O")) {
            arrayEx[x][y] = mark[1].toString()
            break
        } else {
            println("There is already a mark there XD")
            return
        }
    }
}

fun enemyPlay(mark: String) {
    val empty = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (game[i][j] !in listOf("O", "X")) {
                empty.add(Pair(i, j))
            }
        }
    }
    Thread.sleep(1000)
    println("\n☹ Second player is thinking...")
    Thread.sleep(2000)
    if (empty.isNotEmpty()) {
        if (game[1][1] !in listOf("O", "X")) {
            game[1][1] = mark[0].toString()
        } else {
            val choice = empty.random()
            game[choice.first][choice.second] = mark[0].toString()
        }
    }
}

fun notOverVerification(): Boolean {
    for (i in 0 until 3) {
        if (game[i][0] == game[i][1] && game[i][1] == game[i][2] && game[i][2] != " " ||
            game[0][i] == game[1][i] && game[1][i] == game[2][i] && game[2][i] != " "
        ) {
            return false
        }
    }

    if (game[0][0] == game[1][1] && game[1][1] == game[2][2] && game[2][2] != " " ||
        game[0][2] == game[1][1] && game[1][1] == game[2][0] && game[2][0] != " "
    ) {
        return false
    }
    return true
}
