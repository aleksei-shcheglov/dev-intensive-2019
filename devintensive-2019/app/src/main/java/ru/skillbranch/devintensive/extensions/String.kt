package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    val truncatedString: String = when {
        this.length <= 16 -> this
        else -> this.substring(0, value).trimEnd() + "..."
    }
    return truncatedString
}

fun String.stripHtml(): String {
    var newString = ""
    this.trimIndent().forEach { c ->
        when {
            c == ' ' -> when (c) {
                newString[newString.length - 1] -> {}
                else -> newString += c.toString()
            }
            c == '>' -> newString = newString.substring(0, newString.indexOf('<'))
            c == ';' -> newString = newString.substring(0, newString.indexOf('&'))
            else -> newString += c.toString()
        }
    }
    return newString
}