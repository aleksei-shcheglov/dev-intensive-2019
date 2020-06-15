package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView():UserView{

    val nickName = Utils.transliteration("$firstName $lastName", divider = " ")
    val avatar = ""
    val status = if (lastVisit == null) "Newer been" else if (isOnline) "Online" else "Last visit ${lastVisit}"
    val initials = Utils.toInitials(firstName, lastName)

    return UserView(id,
    fullName = "$firstName $lastName",
    nickName = nickName,
    avatar = avatar,
    status = status,
    initials = initials)
}



