package br.com.desafioandroid.app.tool

class DateParse() {

    companion object {

        fun parseDateView(date: String): String{
            var newDateSplit = date.substring(0, 10).split("-")
            return newDateSplit[2] + "/" + newDateSplit[1] + "/" + newDateSplit[0]
        }

    }

}