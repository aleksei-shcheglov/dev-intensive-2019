package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    override val id: String,
    override val chat: Chat,
    override val from: User?,
    override val isIncoming: Boolean = false,
    override val date: Date = Date(),
    val text: String
) : BaseMessage (id ,chat ,from , isIncoming ,date) {
        override fun formatMessages(): String = "id $id ${from?.firstName} ${if(isIncoming) "получил" else "отправил"} сообщение $text ${date.humanizeDiff()}"
}