package es.uam.eps.dadm.cards.src

/**Clase que crea una tarjeta que tenga varias elecciones*/
class Options (
    question: String,
    text: String
): Card(question, text.split(",")[0]) {
    private var answers:  List<String>

    /**Inicializa las distintas respuestas*/
    init {
        answers = text.split(",")
    }

    /**Enseña las opciones de respuesta*/
    private fun showAns () {
        var num = 1
        for (ans in answers) {
            println(" $num. $ans")
            num += 1
        }
    }

    fun getAnswer(i: Int): String {
        return answers[i]
    }

    /**Override de la funcion show*/
    override fun show () {
        answers = answers.shuffled()

        println(" $question ")
        showAns()
        println(" Seleccione la respuesta correcta: ")
        val userAns = readLine()?.toIntOrNull() ?: -1
        quality = if (answers[userAns-1] == answer) {
            println("Correcto")
            5
        } else  {
            println("Error")
            0
        }
    }

    /**Instancia la clase options desde un string*/
    companion object Load{
        fun fromString(cad: String): Options {
            val data = cad.replace(" ","").split("|")
            val card = Options(data[1], data[2].split(",")[0])

            card.answers = data[2].split(",")
            card.quality = data[3].toInt()
            card.repetitions = data[4].toInt()
            card.interval = data[5].toLong()
            card.nextPracticeDate = data[6]
            card.easiness = data[7].toDouble()
            card.date = data[8]
            card.id = data[9]

            return card
        }
    }

    override fun defaultToString(): String{
        var answerSave = ""
        for (ans in answers) {
            answerSave = if (ans == answer) "$ans$answerSave" else "$answerSave,$ans"
        }
        return "$question | $answerSave | $quality | $repetitions | $interval | $nextPracticeDate | $easiness | $date | $id \n"
    }

    override fun showCard () {
        var answerSave = ""
        for (ans in answers) {
            answerSave = if (ans == answer) "$ans$answerSave" else "$answerSave,$ans"
        }
        println("$question -> $answerSave")
    }

    override fun toString() = "options | " + defaultToString()
}