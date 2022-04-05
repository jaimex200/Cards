package es.uam.eps.dadm.cards.src

class Cloze (
    question: String,
    answer: String
): Card(question, answer) {
    /**Override de la clase show*/
    override fun show () {
        print(" $question (INTRO para ver respuesta) ")
        readLine()
        val answerTok = question.split('*')
        print(" ${answerTok[0]}$answer${answerTok[2]} (Teclea 0, 3 o 5): ")
        val userAns = readLine()?.toIntOrNull() ?: -1
        quality = userAns
    }

    /**Instancia la clase cloze desde un string*/
    companion object Load{
        fun fromString(cad: String): Cloze {
            val data = cad.replace(" ","").split("|")
            val card = Cloze(data[1], data[2])

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

    /**Pasa la clase cloze a un string*/
    override fun toString() = "cloze | " + defaultToString()
}