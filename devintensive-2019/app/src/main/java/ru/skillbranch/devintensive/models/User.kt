package ru.skillbranch.devintensive.models

import android.net.Uri
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.utils.Utils
import java.nio.file.attribute.AclEntry
import java.util.*

data class User (
    val id:String,
    var firstName:String?,
    var lastName: String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    var lastVisit:Date? = null,
    var isOnline:Boolean = false

)
{
    var introBeat: String

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(
        id = id,
        firstName = "John" ,
        lastName = "Doe")

    init{
        introBeat = getIntro()

        println("It's Alive!\n" +
                "${if (lastName==="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName"} \n")
    }

    private fun getIntro() : String{
        return ("""
            Oh! Hello!
            $firstName $lastName
        """.trimIndent())
    }

    fun printme() {
        println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: ${lastVisit?.humanizeDiff()}
            isOnline: $isOnline
        """.trimIndent())
    }

    companion object Factory{
        private var lastId : Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }

        fun Builder(): UserBuilder {
            return UserBuilder()
        }
    }

}

class UserBuilder() {
    private var id:String = "0"
    private var firstName:String? = null
    private var lastName: String? = null
    private var avatar:String? = null
    private var rating:Int = 0
    private var respect:Int = 0
    private var lastVisit:Date? = null
    private var isOnline:Boolean = false

    fun build(): User{
        return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }

    fun id(string: String): UserBuilder {
        this.id = string
        return this
    }

    fun firstName(string: String): UserBuilder  {
        this.firstName = string
        return this
    }

    fun lastName(string: String): UserBuilder  {
        this.lastName = string
        return this
    }

    fun avatar(string: String): UserBuilder  {
        this.avatar = string
        return this
    }

    fun rating(int: Int): UserBuilder  {
        this.rating = int
        return this
    }

    fun respect(int: Int): UserBuilder  {
        this.respect = int
        return this
    }

    fun lastVisit(date: Date): UserBuilder  {
        this.lastVisit = date
        return this
    }

    fun isOnline(boolean: Boolean): UserBuilder  {
        this.isOnline = boolean
        return this
    }
}


