package ru.skillbranch.devintensive.utils

import java.util.*
import kotlin.text.trimIndent as trimIndent

object Utils {
    fun parseFullName(fullName: String?) :Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(delimiters = *arrayOf(" "))
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(when(firstName){"" -> null else -> firstName}, when(lastName){"" -> null else -> lastName})
    }

    fun toInitials(firstName:String?, lastName:String?):String?{
        val firstLetter = when(firstName?.trimIndent()){
            null -> ""
            "" -> ""
            else -> firstName[0].toUpperCase()
        }

        val lastLetter = when(lastName?.trimIndent()){
            null -> ""
            "" -> ""
            else ->lastName[0].toUpperCase()
        }

        val initials = "$firstLetter$lastLetter"

        return when(initials){
            "" -> null
            else -> initials
        }
    }

    fun transliteration(payload:String?, divider:String = " "):String{
        val symbolMap = mapOf("а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e",
                "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l",
                "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t",
                "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'",
                "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

        if (payload == null) return ""

        var outputString = ""
        payload.trimIndent().forEach { c ->
            if ("$c" == " ") outputString += divider else{
            var c1= symbolMap.get("$c")
            if (c1==null) {c1 = symbolMap.get("${c.toLowerCase()}")?.toUpperCase(Locale.ROOT)}
            outputString += c1?:"$c"
            }
        }
        return outputString
    }

}

