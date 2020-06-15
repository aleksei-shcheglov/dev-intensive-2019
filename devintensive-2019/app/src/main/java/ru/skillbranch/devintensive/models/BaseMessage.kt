package ru.skillbranch.devintensive.models

import android.service.voice.AlwaysOnHotwordDetector
import java.util.*

abstract class BaseMessage(
    open val id: String,
    open val chat: Chat,
    open val from: User?,
    open val isIncoming: Boolean = false,
    open val date: Date = Date()
)
{
    abstract fun formatMessages():String

    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date = Date(),
            type: String = "text",
            payload: Any?,
            isIncoming: Boolean = false
        ):BaseMessage{
            lastId++
            return when(type){
                "image" -> ImageMessage(id="$lastId", from = from, chat = chat, date = date, image = payload as String, isIncoming = isIncoming)
                else -> TextMessage(id="$lastId", from = from, chat = chat, date = date, text = payload as String, isIncoming = isIncoming)
            }
        }
    }
}