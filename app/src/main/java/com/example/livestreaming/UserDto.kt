package com.example.livestreaming

class UserDto {
    var name: String = ""
    var comment: String = ""

    constructor(name: String, comment: String) {
        this.name = name
        this.comment = comment
    }
}